package org.alexoliveira.bowlingchallenge.domain.model;

import java.util.ArrayList;
import java.util.List;

public class Player {
	
	private int playerNumber;
	
	private String name;
	
	private List<String> shots;
	
	public Player(int playerNumber)
	{
		shots = new ArrayList<String>();
		this.playerNumber = playerNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
