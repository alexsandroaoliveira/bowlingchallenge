package org.alexoliveira.bowlingchallenge.application;

import java.util.List;

import org.alexoliveira.bowlingchallenge.application.interfaces.GameApp;
import org.alexoliveira.bowlingchallenge.domain.interfaces.Game;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.GameFileReader;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.ScoreRender;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrow;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard;
import org.springframework.beans.factory.annotation.Autowired;

public class BowlingGameApp implements GameApp {
	
	@Autowired
	private Game game;

	@Autowired
	private GameFileReader reader;
	
	@Autowired
	private ScoreRender scoreRender;

	public void Run(String[] args) {
		
		try {
			
			String gameFile = getGameFile(args);
			
			List<PlayerThrow> throwList = reader.readFile(gameFile);
			
			for (PlayerThrow playerThrow: throwList) {
				
				game.computeNewThrow(playerThrow.getPlayerName(), playerThrow.getPinfalls());
				
			}
			
			Scoreboard scoreboard = game.getScoreboard();
			
			scoreRender.render(scoreboard);
			
		} catch (Exception e) {
			e.printStackTrace();
			
		}
	}

	private String getGameFile(String[] args) throws Exception {
		if (args.length == 0) {
			throw new Exception("Invalid game file");
		}
		
		String gameFile = args[0];
		return gameFile;
	}

}
