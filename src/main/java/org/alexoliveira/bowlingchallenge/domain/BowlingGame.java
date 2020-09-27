package org.alexoliveira.bowlingchallenge.domain;

import org.alexoliveira.bowlingchallenge.domain.interfaces.Game;
import org.alexoliveira.bowlingchallenge.domain.interfaces.GameEngine;
import org.alexoliveira.bowlingchallenge.domain.interfaces.GameScoreboard;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard;
import org.springframework.beans.factory.annotation.Autowired;

//Proxy Class
public class BowlingGame implements Game {

	@Autowired
	private GameEngine gameEngine;
	
	@Autowired
	private GameScoreboard gameScoreboard;

	@Override
	public void computeNewThrow(String playerName, String pinfalls) throws Exception {
		gameEngine.computeNewThrow(playerName, pinfalls);
	}

	@Override
	public Scoreboard getScoreboard() {
		return gameScoreboard.getScoreboard(gameEngine.getPlayersScores());
	}

}
