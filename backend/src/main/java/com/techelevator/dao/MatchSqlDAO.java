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
	public Match[] getMatchList(Long tournamentId) {
		String sql = "SELECT tournamentid, matchid, is_complete, winner_team_id, round, start_time, end_time " +
				"FROM matches WHERE tournamentid = ? ORDER BY matchid";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, tournamentId);
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
		String sql = "INSERT INTO matches (matchid, is_complete, round, start_time, end_time, tournamentid) VALUES "
				+"(?, ?, ?, ?, ?, ?)";
		newMatch.setMatchid(getNextMatchId());
		jdbcTemplate.update(sql, newMatch.getMatchid(), newMatch.isComplete(), newMatch.getRound(), newMatch.getStartTime(), 
				newMatch.getEndTime(), newMatch.getTournamentId());
		if(newMatch.getTeamList() != null) {
			Team[] teamArray = newMatch.getTeamList();
			for (int i = 0; i < teamArray.length; i++) {
				String sqlTeamMatch = "INSERT INTO team_match (matchid, teamid) VALUES (?, ?)";
				jdbcTemplate.update(sqlTeamMatch, newMatch.getMatchid(), teamArray[i].getTeamId());
			}
//			String sqlLinking = "INSERT INTO team_match (matchid, teamid) VALUES ";
//			Team[] teamArray = newMatch.getTeamList();
//			for(int i = 0; i < teamArray.length; i++) {
//				String newString = "(" + newMatch.getMatchid() + ", " + teamArray[i].getTeamId() + ")";
//				if(i < teamArray.length - 1) {
//					newString += ",";
//				}
//				sqlLinking += newString;
//			}
//			jdbcTemplate.update(sqlLinking);
		}
		return newMatch;
		
	}
	
	@Override
	public Match completeMatch(Match newMatch) {
		String sql = "UPDATE matches SET is_complete = ?, winner_team_id = ? WHERE matchid = ?";
		newMatch.setComplete(true);
		jdbcTemplate.update(sql, newMatch.isComplete(),newMatch.getWinnerTeamId(), newMatch.getMatchid());
		
		sql = "SELECT * FROM matches WHERE tournamentid = ? AND round = ? ORDER BY matchid ASC";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, newMatch.getTournamentId(), newMatch.getRound());
		
		int currentMatchIndex = 0;
		int matchesInCurrentRound = 0;
		//long firstMatchId = 0;
		while(results.next()) {
			/*if(matchesInCurrentRound == 0) {
				firstMatchId = results.getLong("matchid");
			}*/
			if(results.getLong("matchid") == newMatch.getMatchid()) {
				currentMatchIndex = matchesInCurrentRound;
				
			}

			matchesInCurrentRound ++;
		}
		
		long nextMatchIndex = currentMatchIndex / 2;
		SqlRowSet nextRoundResults = jdbcTemplate.queryForRowSet(sql, newMatch.getTournamentId(), newMatch.getRound() + 1);
		if(nextRoundResults.next()) {
			long startingIndex = nextRoundResults.getLong("matchid");
			sql = "INSERT INTO team_match (matchid, teamid) VALUES (?, ?)";
			jdbcTemplate.update(sql,  startingIndex + nextMatchIndex, newMatch.getWinnerTeamId());
		}
		else {
			sql = "UPDATE tournaments SET is_complete = true, winner_team_id = ? WHERE tournamentid = ?";
			jdbcTemplate.update(sql,newMatch.getWinnerTeamId(), newMatch.getTournamentId());
			
			
		}
		return newMatch;
	}
	@Override
	public Team[] getMatchTeams(Match match) {
		String sql = "SELECT teams.teamid, tournamentid, general_manager_id, teamname, seed FROM teams JOIN " +
				"team_match ON teams.teamid = team_match.teamid WHERE matchid = ?";
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
		newTeam.setSeed(results.getLong("seed"));
		if(results.getLong("general_manager_id") > 0) {
			newTeam.setGeneralManagerId(results.getLong("general_manager_id"));
		}
		newTeam.setTeamName(results.getString("teamname"));
		return newTeam;
	}

}
