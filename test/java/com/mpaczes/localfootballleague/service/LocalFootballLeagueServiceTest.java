package com.mpaczes.localfootballleague.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import com.mpaczes.localfootballleague.domain.Player;
import com.mpaczes.localfootballleague.domain.PlayerNotFoundException;
import com.mpaczes.localfootballleague.repository.LocalFootballLeagueRepository;

@RunWith(MockitoJUnitRunner.class)
public class LocalFootballLeagueServiceTest {

	@Mock
	private LocalFootballLeagueRepository repository;
	
	@InjectMocks
	private LocalFootballLeagueService service;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getListOfAllPlayers_returnEmptyList() {
		given(repository.findAll()).willReturn(new ArrayList<Player>());
		
		List<Player> allPlayers = service.getListOfAllPlayers();
		
		assertThat(allPlayers.size()).isEqualTo(0);
	}
	
	@Test
	public void getListOfAllPlayers_returnSingleList() {
		List<Player> players = new ArrayList<Player>();
		
		Player player = new Player(1L, "Robert Lewandowski", 7);
		players.add(player);
		
		given(repository.findAll()).willReturn(players);
		
		List<Player> allPlayers = service.getListOfAllPlayers();
		
		assertThat(allPlayers.size()).isEqualTo(1);
	}
	
	@Test
	public void getListOfAllPlayers_returnOneParticulerPlayer() {
		List<Player> players = new ArrayList<Player>();
		
		Player player = new Player(1L, "Robert Lewandowski", 7);
		players.add(player);
		
		given(repository.findAll()).willReturn(players);
		
		List<Player> allPlayers = service.getListOfAllPlayers();
		
		assertThat(allPlayers.get(0).getPlayerName()).isEqualTo("Robert Lewandowski");
		
		assertThat(allPlayers.get(0).getPlayerId()).isEqualTo(1L);
		
		assertThat(allPlayers.get(0).getNumberOfGoalsInCurrentSeason()).isEqualTo(7);
	}
	
	@Test
	public void getListOfAllPlayers_returnSortedListOfPlayers() {
		List<Player> players = new ArrayList<Player>();
		
		Player player = new Player(1L, "Robert Lewandowski", 7);
		players.add(player);
		
		player = new Player(2L, "Kuba Blaszczykowski", 12);
		players.add(player);
		
		given(repository.findAll()).willReturn(players);
		
		List<Player> allPlayers = service.getListOfAllPlayers();
		
		assertThat(allPlayers.get(0).getPlayerName()).isEqualTo("Kuba Blaszczykowski");
	}
	
	@Test
	public void addNewPlayer_returnOneParticulerPlayer() {
		given(repository.save(any())).willReturn(new Player(1L, "Robert Lewandowski", 7));
		
		Long savedPlayerId = service.addNewPlayer("Robert Lewandowski", 7);
		
		assertThat(savedPlayerId).isEqualTo(1L);
	}
	
	@Test
	public void updatePlayerScore_returnPlayerWithUpdatedScore() {
		given(repository.findById(any())).willReturn(Optional.of(new Player(1L, "Robert Lewandowski", 7)));
		
		Player updatedPlayer = service.updatePlayerScore(1L, 4);
		
		assertThat(updatedPlayer.getNumberOfGoalsInCurrentSeason()).isEqualTo(11);
	}
	
	@Test(expected = PlayerNotFoundException.class)
	public void updatePlayerScore_whenPlayerNotFound() {
		given(repository.findById(any())).willReturn(Optional.empty());
		
		Player updatedPlayer = service.updatePlayerScore(1L, 4);
	}

}
