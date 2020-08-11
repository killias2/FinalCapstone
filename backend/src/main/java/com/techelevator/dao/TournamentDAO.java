package com.techelevator.dao;

import java.util.List;

import com.techelevator.model.Tournament;

public interface TournamentDAO {
	boolean createTournament(Tournament tournament);
	
	List<Tournament> getTournaments();

	Tournament getTournamentById(Long id);

	
	List<Tournament> getTournamentByOrganizerId(Long id);
	
	List<Tournament> getFutureTournaments();
	
	List<Tournament> getCurrentTournaments();

	void deleteTournamentById(Long id);
	
	List<Tournament> getPastTournaments();
	
	
}
