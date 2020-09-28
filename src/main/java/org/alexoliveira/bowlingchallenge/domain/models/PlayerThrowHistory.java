package org.alexoliveira.bowlingchallenge.domain.models;

import java.util.ArrayList;
import java.util.List;

public class PlayerThrowHistory {
	
	private int playerNumber;
	
	private List<ThrowScore> throwList;
	
	private List<PlayerFrame> frameList;
	
	public PlayerThrowHistory(int playerNumber)
	{
		throwList = new ArrayList<ThrowScore>();
		frameList= new ArrayList<PlayerFrame>();
		this.playerNumber = playerNumber;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(int playerNumber) {
		this.playerNumber = playerNumber;
	}

	public List<ThrowScore> getThrowList() {
		return throwList;
	}

	public void setThrowList(List<ThrowScore> shotList) {
		this.throwList = shotList;
	}

	public List<PlayerFrame> getFrameList() {
		return frameList;
	}

	public void setFrameList(List<PlayerFrame> frameList) {
		this.frameList = frameList;
	}

	
}
