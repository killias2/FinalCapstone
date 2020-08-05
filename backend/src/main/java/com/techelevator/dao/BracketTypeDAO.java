package com.techelevator.dao;

import com.techelevator.model.BracketType;

public interface BracketTypeDAO {
	
	boolean createBracketType(BracketType bracketType);
	
	BracketType[] getBracketTypeArray();

}
