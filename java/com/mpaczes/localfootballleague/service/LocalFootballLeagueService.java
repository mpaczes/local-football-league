package com.mpaczes.localfootballleague.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mpaczes.localfootballleague.domain.Player;
import com.mpaczes.localfootballleague.domain.PlayerNotFoundException;
import com.mpaczes.localfootballleague.repository.LocalFootballLeagueRepository;

@Service
public class LocalFootballLeagueService {
	
	@Autowired
	private LocalFootballLeagueRepository repository;
	
	public List<Player> getListOfAllPlayers() {
		Iterable<Player> iterator = repository.findAll();
		
		List<Player> allPlayers = new ArrayList<>();
		
		for (Player player : iterator) {
			allPlayers.add(player);
		}
		
		// descending sort order
		Collections.sort(allPlayers, (Player first, Player second) -> {
			if (first.getNumberOfGoalsInCurrentSeason().compareTo(second.getNumberOfGoalsInCurrentSeason()) == 0) {
				return first.getPlayerName().compareTo(second.getPlayerName());
			} else {
				return first.getNumberOfGoalsInCurrentSeason().compareTo(second.getNumberOfGoalsInCurrentSeason()) * (-1);
			}
		});
		
		return allPlayers;
	}
	
	public Long addNewPlayer(String playerName, Integer numberOfGoalsInCurrentSeason) {
		Player newPlayer = new Player();
		
		newPlayer.setPlayerName(playerName);
		newPlayer.setNumberOfGoalsInCurrentSeason(numberOfGoalsInCurrentSeason);
		
		Player savedPlayer = repository.save(newPlayer);
		
		return savedPlayer.getPlayerId();
	}
	
	public Player updatePlayerScore(Long playerId, Integer numberOfGoalsInCurrentSeason) {
		Optional<Player> foundPlayer = repository.findById(playerId);
		
		Player player = null;
		
		if (foundPlayer.isPresent()) {
			player = foundPlayer.get();
			Integer currentPlayerScore = player.getNumberOfGoalsInCurrentSeason();
			
			player.setNumberOfGoalsInCurrentSeason(currentPlayerScore + numberOfGoalsInCurrentSeason);
			
			repository.save(player);
		} else {
			throw new PlayerNotFoundException("There is no player with id : " + playerId);
		}
		
		return player;
	}
	
	public Iterable<Player> removePlayer(Long playerId) {
		if (repository.existsById(playerId)) {
			repository.deleteById(playerId);
		} else {
			throw new PlayerNotFoundException("There is no player with id : " + playerId);
		}
		
		return repository.findAll();
	}

}
