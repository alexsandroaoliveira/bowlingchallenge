package org.alexoliveira.bowlingchallenge.domain;

import org.alexoliveira.bowlingchallenge.domain.model.configuration.GameConfiguration;

public class GameFactory {

	public static GameEngine Build(GameConfiguration configuration) {
		return new GameEngine(configuration);
	}
	
	public static GameEngine Build() {
	
		GameConfiguration configuration = new GameConfiguration();
		
		return Build(configuration);
	}
	
}
