package org.alexoliveira.bowlingchallenge.domain.model;

public class Frame {
	
	private String playerName;
	
	private int frameNumber;
	
	private int shotNumber;
	
	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public int getFrameNumber() {
		return frameNumber;
	}

	public void setFrameNumber(int frameNumber) {
		this.frameNumber = frameNumber;
	}

	public int getShotNumber() {
		return shotNumber;
	}
	
	public void setShotNumber(int shotNumber) {
		this.shotNumber = shotNumber;
	}
	
}
