package com.techelevator.dao;

import com.techelevator.model.Match;
import com.techelevator.model.Tournament;

public interface MatchDAO {
	
	public Match[] getMatchList(Tournament tournament);
	public Match createMatch(Match newMatch);
		
}
