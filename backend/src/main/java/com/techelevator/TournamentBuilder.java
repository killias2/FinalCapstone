package com.techelevator;

import java.util.Arrays;
import java.util.Collections;
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
	
		
		
		/**
		Team bye = new Team();
		bye.setTeamName("Bye");
		bye.setTeamId((long)-1);
		Team[] teamsAndByes = new Team[(int) Math.pow(2, roundCount)];
		for(int i = 0; i < teamsAndByes.length; i++) {
			if(i < teams.length) {
				teamsAndByes[i] = teams[i];
			}
			else {
				teamsAndByes[i] = bye;
			}
		}**/
		/*if(!tournament.getIsSeeded()) {
			List<Team> teamList = Arrays.asList(teams);
			Collections.shuffle(teamList);
			teams = teamList.toArray(teams);
		}*/
		
		if(!tournament.getIsSeeded()) {
			for(int i = teams.length - 1; i >= 0; i--) {
				int j =(int)( Math.random() * i);
				Team temp = teams[i];
				teams[i] = teams[j];
				teams[j] = temp;
			}
		}
		
		Team[] teamsAndByes = new Team[(int) Math.pow(2, roundCount)];
		System.out.println(teams.length + "     " + teamsAndByes.length);
		for(int i = 0; i < teamsAndByes.length; i++) {
			if(i < teams.length) {
				teamsAndByes[i] = teams[i];
			}
			else {
				System.out.println("test");
				Team t = new Team();
				t.setSeed((long)i+1);
				t.setTeamName("bye" + (i+1));
				t.setTournamentId(tournament.getId());
				teamsAndByes[i] = teamDAO.createTeam(t);
			}
		}
		
		if(!tournament.getIsSeeded()) {
			
		for(int i = 0; i < roundCount; i++) {
			
			for(int j = 0; j < (Math.pow(2,  roundCount) / 2) / Math.pow(2, i); j++) {
			
			Match m = new Match();
			m.setTournamentId(tournament.getId());
			m.setComplete(false);
			m.setRound(i);
			if(i == 0) { //only put teams in for match 0
				Team[] teamsInMatch = new Team[2];
				teamsInMatch[0] = teamsAndByes[j];
				teamsInMatch[1] = teamsAndByes[teamsAndByes.length - j - 1];
				m.setTeamList(teamsInMatch);
			}
		
			matchDAO.createMatch(m);
		}
		}
		}
		else {
			Team[] seededTeams = new Team[teamsAndByes.length];
			for(int i = 0; i < teamsAndByes.length; i++) {
				int seedPosition = seedPlayer(teamsAndByes[i].getSeed(), (int)Math.pow(2,  roundCount));
				seededTeams[seedPosition] = teamsAndByes[i];
			}
			
			
			for(int i = 0; i < roundCount; i++) {
				for(int j = 0; j < (Math.pow(2,  roundCount) / 2) / Math.pow(2, i); j++) {
				Match m = new Match();
				m.setTournamentId(tournament.getId());
				m.setComplete(false);
				m.setRound(i);
				if(i == 0) { //only put teams in for match 0
					Team[] teamsInMatch = new Team[2];
					teamsInMatch[0] = seededTeams[j];
					teamsInMatch[1] = seededTeams[seededTeams.length - j - 1];
					m.setTeamList(teamsInMatch);
				}
				Team[] test = m.getTeamList();
				System.out.println(test.length);
				System.out.println(i);
				System.out.println(test[0].getTeamId());
				System.out.println(test[1].getTeamId());
				matchDAO.createMatch(m);
			}
			}
		}
		
		return true;
	}
	//Seed player code taken from user Absurd-Mind on stackoverflow 
	//https://stackoverflow.com/questions/22959408/algorithm-for-placement-of-32-seeded-players-in-a-128-person-tournament
	public int seedPlayer(long seed, int partSize) {
		if(seed <= 1) {
			return 0;
		}
		
		if(seed % 2 == 0) {
			return partSize / 2 + seedPlayer(seed / 2, partSize / 2);
		}
		return seedPlayer (seed/2 + 1, partSize / 2);
	}
}
