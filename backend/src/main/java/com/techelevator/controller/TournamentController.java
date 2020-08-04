package com.techelevator.controller;

import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.TournamentDAO;
import com.techelevator.model.Tournament;

@RestController
public class TournamentController {
	private TournamentDAO tournamentDAO;
	
	public TournamentController(TournamentDAO tournamentDAO) {
		this.tournamentDAO = tournamentDAO;
	}
	
	@RequestMapping(value = "/tournaments", method = RequestMethod.POST)
	public boolean createTournament(@RequestBody Tournament tournament) {
		return this.tournamentDAO.createTournament(tournament);
	}
}
