package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Tournament;

public interface TournamentDAO {
	boolean createTournament(Tournament tournament);
	
	List<Tournament> getTournaments();

	Tournament getTournamentById(Long id);

	boolean updateTournament(Tournament tournament);
	
	List<Tournament> getTournamentByOrganizerId(Long id);
}
