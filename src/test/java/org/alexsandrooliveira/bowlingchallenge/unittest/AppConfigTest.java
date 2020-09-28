package org.alexsandrooliveira.bowlingchallenge.unittest;

import org.alexoliveira.bowlingchallenge.domain.BowlingGameEngine;
import org.alexoliveira.bowlingchallenge.domain.BowlingGameValidation;
import org.alexoliveira.bowlingchallenge.domain.interfaces.GameEngine;
import org.alexoliveira.bowlingchallenge.domain.interfaces.GameValidation;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.GameConfigReader;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.PlayerThrowHistoryRepository;
import org.alexoliveira.bowlingchallenge.infra.repository.PlayerThrowHistoryMemRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.mockito.Mockito.*;

@Configuration
public class AppConfigTest {
	
	@Bean
	public GameEngine gameEngine() {
		return new BowlingGameEngine();
	}
	
	@Bean
	public GameValidation gameValidation() {
		return mock(BowlingGameValidation.class); 
	}
	
	@Bean
	public GameConfigReader configReader() {
		return mock(GameConfigReader.class); 
	}
	
	@Bean
	public PlayerThrowHistoryRepository playerThrowHistoryRepository() {
		return new PlayerThrowHistoryMemRepository();
	}	
}
