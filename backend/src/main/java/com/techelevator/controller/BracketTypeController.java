package com.techelevator.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.dao.BracketTypeDAO;
import com.techelevator.model.BracketType;

@RestController
@CrossOrigin(origins = "http://localhost:8081", allowedHeaders = "*")
public class BracketTypeController {
	
	private BracketTypeDAO bracketTypeDAO;
	
	public BracketTypeController(BracketTypeDAO bracketTypeDAO) {
		this.bracketTypeDAO = bracketTypeDAO;
	}
	
	@RequestMapping(value = "/brackettype", method = RequestMethod.POST)
	public boolean createBracketType(@RequestBody BracketType bracketType) {
		return bracketTypeDAO.createBracketType(bracketType);
	}
	
	@RequestMapping(value = "/brackettype", method = RequestMethod.GET)
	public BracketType[] getBracketTypeArray() {
		return bracketTypeDAO.getBracketTypeArray();
	}

}
