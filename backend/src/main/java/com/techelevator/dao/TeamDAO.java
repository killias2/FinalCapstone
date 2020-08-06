package com.techelevator.dao;

import org.springframework.stereotype.Service;

import com.techelevator.model.Team;

@Service
public interface TeamDAO {
	
	public boolean createTeam(Team newTeam);
	
	public boolean updateTeam(Team team);
	
<<<<<<< HEAD
=======
	public Team[] getTeamsByTournament(Long id);
	
>>>>>>> 12eccbaf4e3656e2d075392521e6ad3bdefa9ee0
	public Team getTeamById(Long id);

	public Team[] getTeamsByTournament(Long tournamentId);

}
