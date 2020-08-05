package com.techelevator.model;

public class Team {
	
	private Long teamId;
	private Long tournamentId;
	private Long generalManagerId;
	private String teamName;
	
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
