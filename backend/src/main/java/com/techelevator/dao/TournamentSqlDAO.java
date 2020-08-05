package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Tournament;

@Service
public class TournamentSqlDAO implements TournamentDAO{
	private JdbcTemplate jdbcTemplate;
	
	public TournamentSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public boolean createTournament(Tournament tournament) {
		String sql = "INSERT INTO tournaments (t_name, is_open, gameid, bracketid, organizerid, start_date, end_date, is_seeded, is_complete)"
					+ "values(?,?,?,?,?,?,?,?, ?)";
		return 1 == jdbcTemplate.update(sql, tournament.getTournamentName(), tournament.getOpenToJoin(), tournament.getGameId(), tournament.getBracketId(),
										tournament.getTournamentOrganizerId(), tournament.getStartDate(), tournament.getEndDate(), tournament.getIsSeeded(), false);
		
		// TODO Auto-generated method stub
		
	}
	@Override
	public List<Tournament> getTournaments(){
		String sql = "SELECT * FROM tournaments";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		List<Tournament> tournaments = new ArrayList<>();
		while(results.next()) {
			Tournament tournament = mapRowToTournament(results);
			tournaments.add(tournament);
		}
		return tournaments;
		
	}
	@Override
	public Tournament getTournamentById(Long id) {
		String sql = "SELECT * FROM tournaments WHERE tournament_id = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if(results.next()) {
			return mapRowToTournament(results);
		}
		return null;
	}
	
	private Tournament mapRowToTournament(SqlRowSet rs) {
		Tournament tournament = new Tournament();
		tournament.setId(rs.getLong("tournamentid"));
		tournament.setTournamentName(rs.getString("t_name"));
		tournament.setOpenToJoin(rs.getBoolean("is_open"));
		tournament.setGameId(rs.getLong("gameid"));
		tournament.setBracketId(rs.getLong("bracketid"));
		tournament.setIsComplete(rs.getBoolean("is_complete"));
		tournament.setWinnerTeamId(rs.getLong("winner_team_id"));
		tournament.setTournamentOrganizerId(rs.getLong("organizerid"));
		tournament.setStartDate(rs.getDate("start_date").toLocalDate());
		tournament.setEndDate(rs.getDate("end_date").toLocalDate());
		tournament.setIsSeeded(rs.getBoolean("is_seeded"));
		return tournament;
	}
	

}
