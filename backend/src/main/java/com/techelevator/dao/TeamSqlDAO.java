package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Team;

@Service
public class TeamSqlDAO implements TeamDAO {

private JdbcTemplate jdbcTemplate;
	
	public TeamSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
<<<<<<< HEAD
	public boolean createTeam(Team newTeam) {
		String sql = "INSERT INTO teams (teamid, tournamentid, general_manager_id, teamname, seed, team_email_address) VALUES "
				+"(?, ?, ?, ?, ?, ?)";
=======
	public Team createTeam(Team newTeam) {
		String sql = "INSERT INTO teams (teamid, tournamentid, general_manager_id, teamname) VALUES "
				+"(?, ?, ?, ?)";
>>>>>>> 8bcdc55788b3d48045796a9ef7684be15f2183f1
		newTeam.setTeamId(getNextTeamId());
		jdbcTemplate.update(sql, newTeam.getTeamId(), newTeam.getTournamentId(), 
				newTeam.getGeneralManagerId(), newTeam.getTeamName());
		return newTeam;
	}
	
	@Override
	public boolean updateTeam(Team team) {
	String sql = "UPDATE teams  SET (tournamentid, general_manager_id, teamname, team_email_address)"
				+ "= (?, ?, ?) WHERE teamid = ?";
	return 1 == jdbcTemplate.update(sql, team.getTournamentId(), team.getGeneralManagerId(), team.getTeamName(), team.getTeamId(), team.getEmail());
	}

	@Override
<<<<<<< HEAD
	public Team[] getTeamsByTournament(Long id) {
		String sql = "SELECT * FROM teams WHERE tournamentid = ? ORDER BY seed";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
=======
	public Team[] getTeamsByTournament(Long tournamentId) {
		String sql = "SELECT * FROM teams WHERE tournamentid = ?;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tournamentId);
>>>>>>> 8bcdc55788b3d48045796a9ef7684be15f2183f1
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
		String sql = "SELECT * FROM teams WHERE teamid = ? " +
					"ORDER BY seed";
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
<<<<<<< HEAD
		if(results.getLong("seed") > 0) {
			newTeam.setSeed(results.getLong("seed"));
		}
=======
		newTeam.setSeed(results.getLong("seed"));
>>>>>>> 8bcdc55788b3d48045796a9ef7684be15f2183f1
		if(results.getLong("general_manager_id") > 0) {
			newTeam.setGeneralManagerId(results.getLong("general_manager_id"));
		}
		newTeam.setTeamName(results.getString("teamname"));
		newTeam.setEmail(results.getString("team_email_address"));
		return newTeam;
	}

}
