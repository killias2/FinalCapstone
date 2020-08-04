package com.techelevator.dao;

import org.springframework.jdbc.core.JdbcTemplate;
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
		String sql = "INSERT INTO tournaments (t_name, is_open, gameid, bracketid, organizerid, start_date, end_date, is_seeded)"
					+ "values(?,?,?,?,?,?,?,?,?,?";
		return 1 == jdbcTemplate.update(sql, tournament.getTournamentName(), tournament.getOpenToJoin(), tournament.getGameId(), tournament.getBracketId(),
										tournament.getTournamentOrganizerId(), tournament.getStartDate(), tournament.getEndDate(), tournament.getIsSeeded());
		
		// TODO Auto-generated method stub
		
	}

}
