package com.techelevator.dao;

import com.techelevator.model.Team;

public interface TeamDAO {
	
	public Team createTeam(Team newTeam);
	
	public Team[] getAllTeams();
	
	public Team getTeamById(Long id);

}
