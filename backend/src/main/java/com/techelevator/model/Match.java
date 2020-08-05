package com.techelevator.model;

import java.sql.Timestamp;

public class Match {
	
	private Long matchid;
	private boolean isComplete;
	private Long winnerTeamId;
	private int round;
	private Timestamp startTime;
	private Timestamp endTime;
	private Team[] teamList;
	private Long tournamentId;
	
	public Long getTournamentId() {
		return tournamentId;
	}
	public void setTournamentId(Long tournamentId) {
		this.tournamentId = tournamentId;
	}
	public Long getMatchid() {
		return matchid;
	}
	public void setMatchid(Long matchid) {
		this.matchid = matchid;
	}
	public boolean isComplete() {
		return isComplete;
	}
	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}
	public Long getWinnerTeamId() {
		return winnerTeamId;
	}
	public void setWinnerTeamId(Long winnerTeamId) {
		this.winnerTeamId = winnerTeamId;
	}
	public int getRound() {
		return round;
	}
	public void setRound(int round) {
		this.round = round;
	}
	public Timestamp getStartTime() {
		return startTime;
	}
	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}
	public Timestamp getEndTime() {
		return endTime;
	}
	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}
	public Team[] getTeamList() {
		return teamList;
	}
	public void setTeamList(Team[] teamList) {
		this.teamList = teamList;
	}

}
