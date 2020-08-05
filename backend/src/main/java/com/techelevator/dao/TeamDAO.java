package com.techelevator.dao;

import com.techelevator.model.Team;
import com.techelevator.model.Tournament;

public interface TeamDAO {
	
	public Team createTeam(Team newTeam);
	
	public Team updateTeam(Team updatedTeam);
	
	public Team[] getAllTeams();
	
	public Team getTeamById(Long id);

}
