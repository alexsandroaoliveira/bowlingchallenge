package org.alexoliveira.bowlingchallenge.domain.models;

import java.util.ArrayList;
import java.util.List;

public class PlayerThrowHistory {
	
	private int playerNumber;
	
	private List<ThrowScore> shotList;
	
	private List<PlayerFrame> frameList;
	
	public PlayerThrowHistory(int playerNumber)
	{
		shotList = new ArrayList<ThrowScore>();
		frameList= new ArrayList<PlayerFrame>();
		this.playerNumber = playerNumber;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public List<ThrowScore> getShotList() {
		return shotList;
	}

	public void setShotList(List<ThrowScore> shotList) {
		this.shotList = shotList;
	}

	public List<PlayerFrame> getFrameList() {
		return frameList;
	}

	public void setFrameList(List<PlayerFrame> frameList) {
		this.frameList = frameList;
	}

	
}
