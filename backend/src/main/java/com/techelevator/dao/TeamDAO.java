package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.Tournament;

public interface TeamDAO {
	
	public Team createTeam(Team newTeam);
	
	public boolean updateTeam(Team team);
	
	public Team[] getTeamsByTournament(Tournament tournament);
	
	public Team getTeamById(Long id);

}
