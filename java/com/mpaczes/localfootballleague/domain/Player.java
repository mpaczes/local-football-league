package com.mpaczes.localfootballleague.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Player {
	
	@Id
	@GeneratedValue
	private Long playerId;
	private String playerName;
	private Integer numberOfGoalsInCurrentSeason;
	
	public Player() {
		playerId = -1L;
		playerName = "empty player name";
		numberOfGoalsInCurrentSeason = -1;
	}
	
	public Player(Long playerId, String playerName) {
		this.playerId = playerId;
		this.playerName = playerName;
		numberOfGoalsInCurrentSeason = -1;
	}
	
	public Player(Long playerId, String playerName, Integer numberOfGoalsInCurrentSeason) {
		this.playerId = playerId;
		this.playerName = playerName;
		this.numberOfGoalsInCurrentSeason = numberOfGoalsInCurrentSeason;
	}

	public Long getPlayerId() {
		return playerId;
	}

	public void setPlayerId(Long playerId) {
		this.playerId = playerId;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public Integer getNumberOfGoalsInCurrentSeason() {
		return numberOfGoalsInCurrentSeason;
	}

	public void setNumberOfGoalsInCurrentSeason(Integer numberOfGoalsInCurrentSeason) {
		this.numberOfGoalsInCurrentSeason = numberOfGoalsInCurrentSeason;
	}

}
