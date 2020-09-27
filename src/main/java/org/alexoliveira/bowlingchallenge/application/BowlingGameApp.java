package org.alexoliveira.bowlingchallenge.application;

import java.util.List;

import org.alexoliveira.bowlingchallenge.application.interfaces.GameApp;
import org.alexoliveira.bowlingchallenge.domain.interfaces.GameEngine;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.GameFileReader;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.ScoreRender;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrow;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard;
import org.springframework.beans.factory.annotation.Autowired;

public class BowlingGameApp implements GameApp {
	
	@Autowired
	private GameEngine game;

	@Autowired
	private GameFileReader reader;
	
	@Autowired
	private ScoreRender scoreRender;

	public void Run(String gameFile) {
		try {
			
			List<PlayerThrow> gameThrowList = reader.readFile(gameFile);
			
			for (PlayerThrow gt: gameThrowList) {
				
				game.computeNewThrow(gt.getPlayerName(), gt.getPinfalls());
			}
			
			Scoreboard scoreboard = game.getScoreboard();
			
			scoreRender.render(scoreboard);
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
