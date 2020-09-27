package org.alexoliveira.bowlingchallenge.domain.interfaces;

import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard;

public interface Game {
	void computeNewThrow(String playerName, String pinfalls) throws Exception;
	Scoreboard getScoreboard();
}
