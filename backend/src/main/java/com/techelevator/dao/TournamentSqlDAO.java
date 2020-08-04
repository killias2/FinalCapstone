package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.BracketType;
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
	public boolean createBracketType(BracketType bracketType) {
		String sql = "INSERT INTO bracket_types (bracket_name) VALUES (?)";
		jdbcTemplate.update(sql, bracketType.getBracketName());
		return true;
	}
	
	@Override
	public BracketType getBracketType() {
		String sql = "SELECT bracketid, bracket_name FROM bracket_types";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		BracketType returnedBracketType = new BracketType();
		if (results.next()){
			returnedBracketType.setBracketName(results.getString("bracket_name"));
			returnedBracketType.setBracketId(results.getLong("bracketid"));
		}
		return returnedBracketType;
	}

}
