package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.techelevator.dao.TournamentDAO;
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
<<<<<<< HEAD
=======

>>>>>>> 22767cd5415884dcf9ef8af4bb5cbf78da5d88f3
	
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
