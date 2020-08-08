package com.techelevator.dao;

import com.techelevator.model.Match;

public interface MatchDAO {
	
	public Match[] getMatchList(Long tournamentId);
	public Match createMatch(Match newMatch);
	Match completeMatch(Match newMatch);
		
}
