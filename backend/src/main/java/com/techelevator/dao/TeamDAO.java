package com.techelevator.dao;

import org.springframework.stereotype.Service;

import com.techelevator.model.Team;
import com.techelevator.model.Tournament;

@Service
public interface TeamDAO {
	
	public Team createTeam(Team newTeam);
	
	public boolean updateTeam(Team team);
	
	public Team[] getTeamsByTournament(Tournament tournament);
	
	public Team getTeamById(Long id);

}
