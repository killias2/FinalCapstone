package com.techelevator.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	@RequestMapping(value = "/teams", method = RequestMethod.POST)
	public Team createTeam(@RequestBody Team newTeam) {
		return teamDAO.createTeam(newTeam);
	}
	
}
	