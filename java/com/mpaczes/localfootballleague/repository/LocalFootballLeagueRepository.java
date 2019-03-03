package com.mpaczes.localfootballleague.repository;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import com.mpaczes.localfootballleague.domain.Player;

@Repository
public interface LocalFootballLeagueRepository extends CrudRepository<Player, Long> {
	// do nothing ..
}
