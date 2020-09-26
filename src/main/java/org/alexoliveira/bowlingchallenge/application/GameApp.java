package org.alexoliveira.bowlingchallenge.application;

import java.util.List;

import org.alexoliveira.bowlingchallenge.domain.GameEngine;
import org.alexoliveira.bowlingchallenge.domain.GameFactory;
import org.alexoliveira.bowlingchallenge.domain.model.GameThrow;
import org.alexoliveira.bowlingchallenge.domain.model.Scoreboard;
import org.alexoliveira.bowlingchallenge.infra.GameFileReader;
import org.alexoliveira.bowlingchallenge.infra.ScoreRender;

public class GameApp {

	public void Run(String gameFile) throws Exception {
		GameEngine game = GameFactory.Build();
		
		List<GameThrow> gameThrowList = new GameFileReader().readFile(gameFile);

		for (GameThrow gt: gameThrowList) {
			game.computeNewThrow(gt);
		}
		
		Scoreboard scoreboard = game.getScoreboard();
		
		new ScoreRender().render(scoreboard);
		
	
	}

}
