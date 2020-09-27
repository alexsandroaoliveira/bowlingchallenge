package org.alexoliveira.bowlingchallenge.application;

import org.alexoliveira.bowlingchallenge.application.interfaces.GameApp;
import org.alexoliveira.bowlingchallenge.domain.BowlingGame;
import org.alexoliveira.bowlingchallenge.domain.BowlingGameEngine;
import org.alexoliveira.bowlingchallenge.domain.BowlingGameScoreboard;
import org.alexoliveira.bowlingchallenge.domain.interfaces.Game;
import org.alexoliveira.bowlingchallenge.domain.interfaces.GameEngine;
import org.alexoliveira.bowlingchallenge.domain.interfaces.GameScoreboard;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.GameConfigReader;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.GameFileReader;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.ScoreRender;
import org.alexoliveira.bowlingchallenge.infra.BowlingConfigReader;
import org.alexoliveira.bowlingchallenge.infra.BowlingFileReader;
import org.alexoliveira.bowlingchallenge.infra.BowlingScoreRender;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

	@Bean
	public GameApp gameApp() {
		return new BowlingGameApp();
	}
	
	@Bean 
	public Game game() {
		return new BowlingGame();
	}

	@Bean
	public GameEngine gameEngine(){
		return new BowlingGameEngine();
	}
	
	@Bean
	public GameScoreboard gameScoreboard() {
		return new BowlingGameScoreboard();
	}
	
	@Bean
	public GameFileReader reader() {
		return new BowlingFileReader();
	}
	
	@Bean
	public ScoreRender scoreRender() {
		return new BowlingScoreRender();
	}
	
	@Bean
	public GameConfigReader configReader() {
		return new BowlingConfigReader();
	}
	
}
