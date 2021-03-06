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
	public boolean setFull(Tournament tournament) {
		String sql = "UPDATE tournaments SET is_full = ? " +
					"WHERE tournamentid = ?;";
		return 1 == jdbcTemplate.update(sql, tournament.isFull(), tournament.getId());
	}
	@Override
	public List<Tournament> getCurrentTournaments() {
		String sql = "SELECT * FROM tournaments t " +
				"JOIN users ON user_id = organizerid " + 
				"LEFT JOIN games g ON t.gameid = g.gameid " +
				"WHERE CURRENT_DATE BETWEEN start_date AND end_date AND is_complete = false " +
				"ORDER BY start_date DESC, end_date;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		List<Tournament> tournaments = new ArrayList<>();
		while(results.next()) {
			Tournament tournament = mapRowToTournament(results);
			tournaments.add(tournament);
		}
		return tournaments;
	}
	@Override
	public List<Tournament> getFutureTournaments() {
		String sql = "SELECT * FROM tournaments t " +
				"JOIN users ON user_id = organizerid " + 
				"JOIN games g ON t.gameid = g.gameid " +
				"WHERE CURRENT_DATE < start_date AND is_complete = false " +
				"ORDER BY start_date;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		List<Tournament> tournaments = new ArrayList<>();
		while(results.next()) {
			Tournament tournament = mapRowToTournament(results);
			tournaments.add(tournament);
		}
		return tournaments;
	}
	@Override
	public List<Tournament> getPastTournaments() {
		String sql = "SELECT * FROM tournaments t " +
				"JOIN users ON user_id = organizerid " + 
				"JOIN games g ON t.gameid = g.gameid " +
				"WHERE CURRENT_DATE > end_date OR is_complete = true " +
				"ORDER BY end_date DESC;";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		List<Tournament> tournaments = new ArrayList<>();
		while(results.next()) {
			Tournament tournament = mapRowToTournament(results);
			tournaments.add(tournament);
		}
		return tournaments;
	}
	
	@Override
	public boolean createTournament(Tournament tournament) {
		String sql = "INSERT INTO tournaments (t_name, is_open, gameid, bracketid, organizerid, start_date, end_date, is_seeded, is_complete, number_of_teams, is_full)"
					+ "values(?,?,?,?,?,?,?,?, ?, ?, ?)";
		return 1 == jdbcTemplate.update(sql, tournament.getTournamentName(), tournament.getOpenToJoin(), tournament.getGameId(), tournament.getBracketId(),
										tournament.getTournamentOrganizerId(), tournament.getStartDate(), tournament.getEndDate(), tournament.getIsSeeded(), false, tournament.getNumberOfTeams(), false);
		
	}
	@Override
	public List<Tournament> getTournaments(){
		String sql = "SELECT * FROM tournaments t " +
				"JOIN users ON user_id = organizerid " + 
				"LEFT JOIN games g ON t.gameid = g.gameid; ";
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
		String sql = "SELECT * FROM tournaments t " + 
				"JOIN users ON user_id = organizerid " + 
				"JOIN games g ON t.gameid = g.gameid " +
				"WHERE tournamentid = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		if(results.next()) {
			return mapRowToTournament(results);
		}
		return null;
	}
	@Override
	public void deleteTournamentById(Long id) {
		String sql = "DELETE FROM teams WHERE tournamentid = ?";
		jdbcTemplate.update(sql, id);
		sql = "DELETE FROM tournaments WHERE tournamentid = ?";
		jdbcTemplate.update(sql, id);
	}
	
	@Override
	public List<Tournament> getTournamentByOrganizerId(Long id) {
		List<Tournament> tournaments = new ArrayList<>();
		String sql = "SELECT * " + 
				"FROM tournaments t " + 
				"JOIN users ON user_id = organizerid " + 
				"LEFT JOIN games g ON t.gameid = g.gameid " +
				"WHERE organizerid = ?; ";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, id);
		while(results.next()) {
			Tournament tournament = mapRowToTournament(results);
			tournaments.add(tournament);
		}
		return tournaments;
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
		tournament.setNumberOfTeams(rs.getLong("number_of_teams"));
		tournament.setGameName(rs.getString("game_name"));
		tournament.setGameDescription(rs.getString("game_description"));
		tournament.setFull(rs.getBoolean("is_full"));
		return tournament;
	}
	

	
}
