package org.alexoliveira.bowlingchallenge.infra.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.PlayerThrowHistoryRepository;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrowHistory;

public class PlayerThrowHistoryMemRepository implements PlayerThrowHistoryRepository {

	private Map<String, PlayerThrowHistory> gameThrowHistory;
	
	public PlayerThrowHistoryMemRepository() {
		gameThrowHistory = new HashMap<String, PlayerThrowHistory>();
	}
	
	@Override
	public PlayerThrowHistory getPlayersScores(int gameId, String playerName) {
		return gameThrowHistory.get(playerName);
	}

	@Override
	public boolean isPlayerExists(int gameId, String playerName) {
		return gameThrowHistory.containsKey(playerName);
	}

	@Override
	public List<String> getPlayers(int gameId) {
		return new ArrayList<>(gameThrowHistory.keySet());
	}

	@Override
	public void insert(int gameId, String playerName, PlayerThrowHistory playerThrowHistory) {
		gameThrowHistory.put(playerName, playerThrowHistory);		
	}

	@Override
	public PlayerThrowHistory get(int gameId, String playerName) {
		return gameThrowHistory.get(playerName);
	}

	@Override
	public Map<String, PlayerThrowHistory> getAll() {
		return gameThrowHistory;
	}

}
