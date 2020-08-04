package com.techelevator.dao;

import com.techelevator.model.BracketType;
import com.techelevator.model.Tournament;

public interface TournamentDAO {
	boolean createTournament(Tournament tournament);
	
	boolean createBracketType(BracketType bracketType);
	
	BracketType getBracketType();
}
