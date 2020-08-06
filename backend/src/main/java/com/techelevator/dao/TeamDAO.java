package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.Tournament;

public interface TeamDAO {
	
	public boolean createTeam(Team newTeam);
	
	public boolean updateTeam(Team team);
	
	public Team[] getTeamsByTournament(Long id);
	
	public Team getTeamById(Long id);

}
