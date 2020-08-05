package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.TournamentDAO;
import com.techelevator.model.BracketType;
import com.techelevator.model.Tournament;

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
	
	@RequestMapping(value = "/tournaments", method = RequestMethod.GET)
	public List<Tournament> getTournaments(){
		return tournamentDAO.getTournaments();
	}
	@RequestMapping(value = "/tournaments/{id}", method = RequestMethod.GET)
	public Tournament getTournamentById(@PathVariable long id) {
		return tournamentDAO.getTournamentById(id);
	}
	@RequestMapping(value = "/tournaments", method = RequestMethod.PUT)
	public boolean completeTournament(@RequestBody Tournament tournament) {
		return false;
	}
	
}
