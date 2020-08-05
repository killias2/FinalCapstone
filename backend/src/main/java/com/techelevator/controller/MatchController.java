package com.techelevator.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.MatchDAO;
import com.techelevator.model.Match;

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
	
	

}
