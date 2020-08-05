package com.techelevator.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.model.Match;
import com.techelevator.model.Team;
import com.techelevator.model.Tournament;

@Service
public class MatchSqlDAO implements MatchDAO {
	
	private JdbcTemplate jdbcTemplate;
	
	public MatchSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public Match[] getMatchList(Tournament tournament) {
		String sql = "SELECT tournamentid, matchid, is_complete, winner_team_id, round, start_time, end_time " +
				"FROM matches WHERE tournamentid = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tournament.getId());
		List<Match> matchList = new ArrayList<>();
		while(results.next()) {
			Match newMatch = mapRowToMatch(results);
			Team[] teamsPlaying = getMatchTeams(newMatch);
			newMatch.setTeamList(teamsPlaying);
			matchList.add(newMatch);
		}
		Match[] matchArray = new Match [matchList.size()];
		matchArray = matchList.toArray(matchArray);
		
		return matchArray;
	}

	@Override
	public Match createMatch(Match newMatch) {
		String sql = "INSERT INTO matches (is_complete, round, start_time, end_time, tournamentid) VALUES "
				+"(?, ?, ?, ?, ?)";
		newMatch.setMatchid(getNextMatchId());
		jdbcTemplate.update(sql, newMatch.isComplete(), newMatch.getRound(), newMatch.getStartTime(), 
				newMatch.getEndTime(), newMatch.getTournamentId());
		return newMatch;
		
	}
	
	public Team[] getMatchTeams(Match match) {
		String sql = "SELECT t.teamid, tournamentid, general_manager_id, teamname FROM teams t JOIN " +
				"team_match tm ON t.teamid = tm.teamid WHERE matchid = ?";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, match.getMatchid());
		List<Team> teamList = new ArrayList<>();
		while(results.next()) {
			Team newTeam = mapRowToTeam(results);
			teamList.add(newTeam);
		}
		Team[] teamArray = new Team [teamList.size()];
		teamArray = teamList.toArray(teamArray);
		return teamArray;
	}
	
	public Long getNextMatchId() {
		SqlRowSet nextIdResult = jdbcTemplate.queryForRowSet("SELECT nextval('matches_matchid_seq')");
		if(nextIdResult.next()) {
			return nextIdResult.getLong(1);
		} else {
			throw new RuntimeException ("Something went wrong while getting a new match ID");
		}
	}
	
	private Match mapRowToMatch(SqlRowSet results) {
		Match newMatch = new Match();
		newMatch.setMatchid(results.getLong("matchid"));
		newMatch.setTournamentId(results.getLong("tournamentid"));
		newMatch.setComplete(results.getBoolean("is_complete"));
		if(results.getLong("winner_team_id") > 0) {
			newMatch.setWinnerTeamId(results.getLong("winner_team_id"));
		}
		newMatch.setRound(results.getInt("round"));
		newMatch.setStartTime(results.getTimestamp("start_time"));
		newMatch.setEndTime(results.getTimestamp("end_time"));
		return newMatch;
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
