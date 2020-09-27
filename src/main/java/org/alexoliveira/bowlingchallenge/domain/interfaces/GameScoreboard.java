package org.alexoliveira.bowlingchallenge.domain.interfaces;

import java.util.Map;

import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrowHistory;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard;

public interface GameScoreboard {
	Scoreboard getScoreboard(Map<String, PlayerThrowHistory> playersScores);
}
