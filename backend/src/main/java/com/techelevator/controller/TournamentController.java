package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.techelevator.TournamentBuilder;
import com.techelevator.dao.MatchDAO;
import com.techelevator.dao.TeamDAO;
import com.techelevator.dao.TournamentDAO;
import com.techelevator.model.Tournament;

@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
@RestController
public class TournamentController {
	private TournamentDAO tournamentDAO;
	private TeamDAO teamDAO;
	private MatchDAO matchDAO;
	
	public TournamentController(TournamentDAO tournamentDAO, TeamDAO teamDAO, MatchDAO matchDAO) {
		this.tournamentDAO = tournamentDAO;
		this.teamDAO = teamDAO;
		this.matchDAO = matchDAO;
	}
	
	@RequestMapping(value = "/tournaments", method = RequestMethod.POST)
	public boolean createTournament(@RequestBody Tournament tournament) {
		return tournamentDAO.createTournament(tournament);
	}
	
	@RequestMapping(value = "/tournaments", method = RequestMethod.GET)
	public List<Tournament> getTournaments(){
		return tournamentDAO.getTournaments();
	}
	@RequestMapping(value = "/tournaments/past", method = RequestMethod.GET)
	public List<Tournament> getPastTournaments(){
		return tournamentDAO.getCompleteTournaments();
	}
	@RequestMapping(value = "/tournaments/current", method = RequestMethod.GET)
	public List<Tournament> getCurrentTournaments(){
		return tournamentDAO.getCurrentTournaments();
	}
	@RequestMapping(value = "/tournaments/{id}", method = RequestMethod.GET)
	public Tournament getTournamentById(@PathVariable long id) {
		return tournamentDAO.getTournamentById(id);
	}
	@RequestMapping(value = "/tournaments", method = RequestMethod.PUT)
	public boolean completeTournament(@RequestBody Tournament tournament) {
		TournamentBuilder t = new TournamentBuilder(tournament, teamDAO, matchDAO);
		return t.buildTournament();
	}
	@RequestMapping(value = "/tournament/my-tournaments/{id}", method = RequestMethod.GET)
	public List<Tournament> getTournamentByOrganizerId(@PathVariable long id) {
		return tournamentDAO.getTournamentByOrganizerId(id);
	}
	@RequestMapping(value = "/tournaments/{id}", method = RequestMethod.DELETE)
	public void deleteTournamentById(@PathVariable long id) {
		tournamentDAO.deleteTournamentById(id);
	}
	
}
