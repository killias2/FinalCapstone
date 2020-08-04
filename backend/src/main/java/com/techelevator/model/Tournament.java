package com.techelevator.model;

import java.time.LocalDate;

public class Tournament {
	
	

	private Long id;
	private String tournamentName;
	private Long tournamentOrganizerId;
	private Long[] teamIds;
	private Boolean isSeeded;
	private Long gameId;
	private String tournamentDescription;
	private Long bracketId;
	private Boolean openToJoin;
	private LocalDate startTime;
	private LocalDate endTime;
	
	public Tournament(Long id, String tournamentName, Long tournamentOrganizerId, Long[] teamIds, Boolean isSeeded, Long gameId, String tournamentDescription
					, Long bracketId, Boolean openToJoin, LocalDate startTime, LocalDate endTime) {
		this.id = id;
		this.tournamentName = tournamentName;
		this.tournamentOrganizerId = tournamentOrganizerId;
		this.teamIds = teamIds;
		this.isSeeded = isSeeded;
		this.gameId = gameId;
		this.tournamentDescription = tournamentDescription;
		this.bracketId = bracketId;
		this.openToJoin = openToJoin;
		this.startTime = startTime;
		this.endTime = endTime;
		
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTournamentName() {
		return tournamentName;
	}

	public void setTournamentName(String tournamentName) {
		this.tournamentName = tournamentName;
	}

	public Long getTournamentOrganizerId() {
		return tournamentOrganizerId;
	}

	public void setTournamentOrganizerId(Long tournamentOrganizerId) {
		this.tournamentOrganizerId = tournamentOrganizerId;
	}

	public Long[] getTeamIds() {
		return teamIds;
	}

	public void setTeamIds(Long[] teamIds) {
		this.teamIds = teamIds;
	}

	public Boolean getIsSeeded() {
		return isSeeded;
	}

	public void setIsSeeded(Boolean isSeeded) {
		this.isSeeded = isSeeded;
	}

	public Long getGameId() {
		return gameId;
	}

	public void setGameId(Long gameId) {
		this.gameId = gameId;
	}

	public String getTournamentDescription() {
		return tournamentDescription;
	}

	public void setTournamentDescription(String tournamentDescription) {
		this.tournamentDescription = tournamentDescription;
	}

	public Long getBracketId() {
		return bracketId;
	}

	public void setBracketId(Long bracketId) {
		this.bracketId = bracketId;
	}

	public Boolean getOpenToJoin() {
		return openToJoin;
	}

	public void setOpenToJoin(Boolean openToJoin) {
		this.openToJoin = openToJoin;
	}

	public LocalDate getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalDate startTime) {
		this.startTime = startTime;
	}

	public LocalDate getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalDate endTime) {
		this.endTime = endTime;
	}
	
}
