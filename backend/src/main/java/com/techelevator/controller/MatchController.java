package com.techelevator.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.MatchDAO;
import com.techelevator.model.Match;
import com.techelevator.model.Team;

@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
@RestController
public class MatchController {
	private MatchDAO matchDAO;
	
	public MatchController(MatchDAO matchDAO) {
		this.matchDAO = matchDAO;
	}
	
	@RequestMapping(value = "/matches", method = RequestMethod.POST)
	public Match createMatch(@RequestBody Match newMatch) {
		return matchDAO.createMatch(newMatch);
	}
	@RequestMapping(value = "/matches/{id}/complete", method = RequestMethod.PUT)
	public Match completeMatch(@RequestBody Match match) {
		return matchDAO.completeMatch(match);
	}
	
	@RequestMapping(value = "/tournaments/{tournamentId}/matches", method = RequestMethod.GET)
	public Match[] getMatchesForTournament(@PathVariable Long tournamentId) {
		return matchDAO.getMatchList(tournamentId);
	}
	
	@RequestMapping(value = "/matches/{matchId}/teams", method = RequestMethod.GET)
	public Team[] getMatch(@PathVariable Long matchId) {
		Match match = new Match();
		match.setMatchid(matchId);
		return matchDAO.getMatchTeams(match);
	}

}
