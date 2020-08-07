package com.techelevator.dao;

import org.springframework.stereotype.Service;

import com.techelevator.model.Team;

@Service
public interface TeamDAO {
	
	public boolean createTeam(Team newTeam);
	
	public boolean updateTeam(Team team);
	

	public Team[] getTeamsByTournament(Long id);
	
	public Team getTeamById(Long id);

}
