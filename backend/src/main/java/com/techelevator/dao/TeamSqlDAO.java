package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.model.Team;

public class TeamSqlDAO implements TeamDAO {
	
private JdbcTemplate jdbcTemplate;
	
	public TeamSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Team createTeam(Team newTeam) {
		String sql = "INSERT INTO teams (teamid, tournamentid, general_manager_id, teamname) VALUES "
				+"(?, ?, ?, ?)";
		newTeam.setTeamId(getNextTeamId());
		jdbcTemplate.update(sql, newTeam.getTeamId(), newTeam.getTournamentId(), 
				newTeam.getGeneralManagerId(), newTeam.getTeamName());
		return newTeam;
	}

	@Override
	public Team[] getAllTeams() {
		String sql = "SELECT * FROM teams";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		List<Team> teamList = new ArrayList<Team>();
		while(results.next()) {
			Team newTeam = mapRowToTeam(results);
			teamList.add(newTeam);
		}
		Team[] teamArray = new Team [teamList.size()];
		teamArray = teamList.toArray(teamArray);
		return teamArray;
	}

	@Override
	public Team getTeamById(Long id) {
		String sql = "SELECT * FROM teams WHERE teamid = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if(results.next()) {
			return mapRowToTeam(results);
		}
		return null;
	}
	
	public Long getNextTeamId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('teams_teamid_seq')");
		if(nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException ("Something went wrong while getting a new team ID");
		}
	}
	
	private Team mapRowToTeam(SqlRowSet results) {
		Team newTeam = new Team();
		newTeam.setTeamId(results.getLong("teamid"));
		newTeam.setTournamentId(results.getLong("tournamentid"));
		if(results.getLong("general_manager_id") > 0) {
			newTeam.setGeneralManagerId(results.getLong("generalManagerId"));
		}
		newTeam.setTeamName(results.getString("teamname"));
		return newTeam;
	}

}
