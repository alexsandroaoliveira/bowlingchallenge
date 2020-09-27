package org.alexoliveira.bowlingchallenge.domain.interfaces;

import java.util.Map;

import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrowHistory;

public interface GameEngine {
	void computeNewThrow(String playerName, String pinfalls) throws Exception;
	Map<String, PlayerThrowHistory> getPlayersScores();
}
