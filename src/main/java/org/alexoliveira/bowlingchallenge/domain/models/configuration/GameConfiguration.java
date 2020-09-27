package org.alexoliveira.bowlingchallenge.domain.models.configuration;

import java.util.ArrayList;
import java.util.List;

public class GameConfiguration {
	
	private int playersNumber;
	
	private List<FrameConfiguration> frames;
	
	public GameConfiguration() {
		frames = new ArrayList<FrameConfiguration>();
	}

	public int getPlayersNumber() {
		return playersNumber;
	}

	public void setPlayersNumber(int playersNumber) {
		this.playersNumber = playersNumber;
	}

	public List<FrameConfiguration> getFrames() {
		return frames;
	}

	public void setFrames(List<FrameConfiguration> frames) {
		this.frames = frames;
	}	
}
