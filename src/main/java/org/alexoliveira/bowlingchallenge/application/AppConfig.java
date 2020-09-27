package org.alexoliveira.bowlingchallenge.application;

import org.alexoliveira.bowlingchallenge.application.interfaces.GameApp;
import org.alexoliveira.bowlingchallenge.domain.BowlingGameEngine;
import org.alexoliveira.bowlingchallenge.domain.interfaces.GameEngine;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.GameFileReader;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.ScoreRender;
import org.alexoliveira.bowlingchallenge.domain.models.configuration.GameConfiguration;
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
	public GameEngine gameEngine(){
		return new BowlingGameEngine();
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
	public GameConfiguration gameConfiguration() {
		return new GameConfiguration();
	}
	
}
