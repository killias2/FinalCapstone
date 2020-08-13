package com.techelevator.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.techelevator.model.Team;

@Service
public interface TeamDAO {
	
	public Team createTeam(Team newTeam);
	
	public boolean updateTeam(Team team);
	
	public Team getTeamById(Long id);
	
	public Team[] getTeamsByTournament(Long id);
	
	public void removeTeam(Long id);
	
	public List<Team> getWinnerList();

	public Team[] getTeamsByGmId(Long id);

}
