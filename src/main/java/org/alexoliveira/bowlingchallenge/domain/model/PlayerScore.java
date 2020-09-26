package org.alexoliveira.bowlingchallenge.domain.model;

import java.util.ArrayList;
import java.util.List;

public class PlayerScore {
	
	private int playerNumber;
	
	private List<String> shots;
	
	public PlayerScore(int playerNumber)
	{
		shots = new ArrayList<String>();
		this.playerNumber = playerNumber;
	}

	public List<String> getShots() {
		return shots;
	}

	public void setShots(List<String> shots) {
		this.shots = shots;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}
	
}
