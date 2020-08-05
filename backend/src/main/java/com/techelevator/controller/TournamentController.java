package com.techelevator.controller;

import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.TournamentDAO;
import com.techelevator.model.BracketType;
import com.techelevator.model.Tournament;

@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
@RestController
public class TournamentController {
	private TournamentDAO tournamentDAO;
	
	public TournamentController(TournamentDAO tournamentDAO) {
		this.tournamentDAO = tournamentDAO;
	}
	
	@RequestMapping(value = "/tournaments", method = RequestMethod.POST)
	public boolean createTournament(@RequestBody Tournament tournament) {
		return tournamentDAO.createTournament(tournament);
	}
	
	@RequestMapping(value = "/brackettype", method = RequestMethod.POST)
	public boolean createBracketType(@RequestBody BracketType bracketType) {
		return tournamentDAO.createBracketType(bracketType);
	}
	
	@RequestMapping(value = "/brackettype", method = RequestMethod.GET)
	public BracketType getBracketType() {
		return tournamentDAO.getBracketType();
	}
}
