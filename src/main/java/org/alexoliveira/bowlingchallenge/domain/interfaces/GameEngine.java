package org.alexoliveira.bowlingchallenge.domain.interfaces;

public interface GameEngine {
	void computeNewThrow(String playerName, String pinfalls) throws Exception;
}
