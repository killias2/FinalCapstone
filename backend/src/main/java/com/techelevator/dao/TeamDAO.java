package com.techelevator.dao;

import com.techelevator.model.Team;

public interface TeamDAO {
	
	public Team createTeam(Team newTeam);
	
	public boolean updateTeam(Team team);
	
	public Team getTeamById(Long id);

	public Team[] getTeamsByTournament(Long tournamentId);

}
