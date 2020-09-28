package org.alexoliveira.bowlingchallenge.domain.interfaces.infra;

import java.util.List;
import java.util.Map;

import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrowHistory;

public interface PlayerThrowHistoryRepository {
	PlayerThrowHistory getPlayersScores(int gameId, String playerName);

	boolean isPlayerExists(int gameId, String playerName);

	List<String> getPlayers(int gameId);

	void insert(int gameId, String playerName, PlayerThrowHistory playerThrowHistory);

	PlayerThrowHistory get(int gameId, String playerName);

	Map<String, PlayerThrowHistory> getAll();
}
