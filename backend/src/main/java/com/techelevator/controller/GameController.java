package com.techelevator.controller;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.GameDAO;
import com.techelevator.model.Game;

@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
@RestController
public class GameController {
	private GameDAO gameDAO;
	
	public GameController(GameDAO gameDAO) {
		this.gameDAO = gameDAO;
	}
	
	@RequestMapping(value = "/games", method = RequestMethod.GET)
	public List<Game> getGames(){
		return gameDAO.getGames();
	}
}
