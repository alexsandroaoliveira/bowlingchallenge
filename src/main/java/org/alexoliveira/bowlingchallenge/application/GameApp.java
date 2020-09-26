package org.alexoliveira.bowlingchallenge.application;

import java.util.List;

import org.alexoliveira.bowlingchallenge.domain.GameEngine;
import org.alexoliveira.bowlingchallenge.domain.GameFactory;
import org.alexoliveira.bowlingchallenge.domain.model.PlayerThrow;
import org.alexoliveira.bowlingchallenge.domain.model.Scoreboard;
import org.alexoliveira.bowlingchallenge.infra.GameFileReader;
import org.alexoliveira.bowlingchallenge.infra.ScoreRender;

public class GameApp {

	public void Run(String gameFile) throws Exception {
		GameEngine game = GameFactory.Build();
		
		System.out.println("Loading file");
		
		List<PlayerThrow> gameThrowList = new GameFileReader().readFile(gameFile);
		
		System.out.println("File loaded: " + gameThrowList.size());

		for (PlayerThrow gt: gameThrowList) {
			
			System.out.println("Computing new throw: "+ gt.getPlayerName() +"-"+gt.getPinfalls());

			game.computeNewThrow(gt.getPlayerName(), gt.getPinfalls());
		}
		
		System.out.println("Getting scoreboard");

		Scoreboard scoreboard = game.getScoreboard();
		
		System.out.println("Rendering scoreboard");

		new ScoreRender().render(scoreboard);
	}

}
