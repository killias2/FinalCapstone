package com.techelevator.model;

public class Team {
	
	private Long teamId;
	private Long tournamentId;
	private Long generalManagerId;
	private String teamName;
	private Long seed;
	private String email;
	
	public Long getSeed() {
		return seed;
	}
	public void setSeed(Long seed) {
		this.seed = seed;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getTeamId() {
		return teamId;
	}
	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}
	public Long getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}
	public Long getGeneralManagerId() {
		return generalManagerId;
	}
	public void setGeneralManagerId(Long generalManagerId) {
		this.generalManagerId = generalManagerId;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
