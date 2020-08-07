package com.techelevator;

import java.util.List;

import com.techelevator.dao.MatchDAO;
import com.techelevator.dao.TeamDAO;
import com.techelevator.model.Match;
import com.techelevator.model.Team;
import com.techelevator.model.Tournament;

public class TournamentBuilder {
	private Tournament tournament;
	private Team[] teams;
	private MatchDAO matchDAO;
	private TeamDAO teamDAO;
	public TournamentBuilder(Tournament tournament, TeamDAO teamDAO, MatchDAO matchDAO) {
		this.tournament = tournament;
		this.teams = teamDAO.getTeamsByTournament(tournament.getId());
		this.matchDAO = matchDAO;
		this.teamDAO = teamDAO;
	}
	
	public boolean buildTournament() {
		int roundCount = 0;
		while(Math.pow(2, roundCount) < teams.length ) {
			roundCount ++;
		}
		Team bye = new Team();
		// we need to have a predetermined id saved for the bye team
		
		bye.setTeamName("Bye");
		for(int i = 0; i < roundCount; i++) {
			for(int j = 0; j < (teams.length / 2) / (i + 1); j++) {
			Match m = new Match();
			m.setTournamentId(tournament.getId());
			m.setComplete(false);
			if( i == 0) { //only put teams in for match 0
				Team[] teamsInMatch = new Team[2];
				if(2 * i < teams.length) {
					teamsInMatch[0] = teams[2 * i];
				}
				else {
					teamsInMatch[0] = bye;
				}
				if(2 * i < teams.length - 1) {
					teamsInMatch[1] = teams[(2 * i) + 1];
				}
				else {
					teamsInMatch[1] = bye;
				}
				m.setTeamList(teamsInMatch);
			}
			matchDAO.createMatch(m);
			
		}
		}
		
		return true;
	}
}
