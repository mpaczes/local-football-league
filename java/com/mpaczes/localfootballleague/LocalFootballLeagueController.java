package com.mpaczes.localfootballleague;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.mpaczes.localfootballleague.domain.Player;
import com.mpaczes.localfootballleague.domain.PlayerNotFoundException;
import com.mpaczes.localfootballleague.service.LocalFootballLeagueService;

@Controller
@RequestMapping(value = "/football")
public class LocalFootballLeagueController {

	@Autowired
	private LocalFootballLeagueService service;
	
	@RequestMapping(value = "/players", method = RequestMethod.GET)
	@ResponseBody
	public List<Player> getListOfAllPlayers() {
		return service.getListOfAllPlayers();
	}
	
	@RequestMapping(value = "/player", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.OK)
	public void addNewPlayer(@RequestBody Player player) {
		service.addNewPlayer(player.getPlayerName(), player.getNumberOfGoalsInCurrentSeason());
	}
	
	@RequestMapping(value = "/player/{playerId}", method = RequestMethod.PUT)
	@ResponseBody
	public Player updatePlayerScore(@PathVariable Long playerId, @RequestBody Player player) {
		return service.updatePlayerScore(playerId, player.getNumberOfGoalsInCurrentSeason());
	}
	
	@RequestMapping(value = "/player/{playerId}", method = RequestMethod.DELETE)
	@ResponseBody
	public Iterable<Player> removePlayer(@PathVariable Long playerId) {
		return service.removePlayer(playerId);
	}
	
	@ExceptionHandler
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public void playerNotFoundHandler(PlayerNotFoundException exception) {
		// do nothing ..
	}
}
