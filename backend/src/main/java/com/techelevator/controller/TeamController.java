package com.techelevator.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.TeamDAO;
import com.techelevator.model.Team;


@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
@RestController
public class TeamController {
	private TeamDAO teamDAO;
	
	public TeamController(TeamDAO teamDAO) {
		this.teamDAO = teamDAO;
	}
	
<<<<<<< HEAD
	@RequestMapping(value = "/teams", method = RequestMethod.POST) 
	public Team createTeam(@RequestBody Team newTeam) {
=======
	@RequestMapping(value = "/teams", method = RequestMethod.POST)
	public boolean createTeam(@RequestBody Team newTeam) {
		System.out.println(newTeam.getTeamName());
>>>>>>> 12eccbaf4e3656e2d075392521e6ad3bdefa9ee0
		return teamDAO.createTeam(newTeam);
	}
	
	@RequestMapping(value = "/tournaments/{id}/teams", method = RequestMethod.GET)
	public Team[] getTeams(@PathVariable Long id) {
<<<<<<< HEAD
		return teamDAO.getTeamsByTournament(id);
=======
	return teamDAO.getTeamsByTournament(id);
>>>>>>> 12eccbaf4e3656e2d075392521e6ad3bdefa9ee0
	}
	
}
	