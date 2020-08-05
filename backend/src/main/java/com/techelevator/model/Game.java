package com.techelevator.model;

public class Game {


	private Long gameId;
	private String gameName;
	private String gameDescription;
	
	public Game() {
		
	}
	
	public Game(String gameName, String gameDescription) {
		this.gameName = gameName;
		this.gameDescription = gameDescription;
	}
	
	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getGameDescription() {
		return gameDescription;
	}

	public void setGameDescription(String gameDescription) {
		this.gameDescription = gameDescription;
	}
}
