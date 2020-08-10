package com.techelevator.dao;

import com.techelevator.model.Match;
import com.techelevator.model.Team;

public interface MatchDAO {
	
	public Match[] getMatchList(Long tournamentId);
	public Match createMatch(Match newMatch);
	Match completeMatch(Match newMatch);
	public Team[] getMatchTeams(Match match);
}
