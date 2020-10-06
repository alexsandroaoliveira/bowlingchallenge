package org.alexoliveira.bowlingchallenge.domain.models.configuration;

import java.util.ArrayList;
import java.util.List;

public class GameConfiguration {
	
	private List<FrameConfiguration> frames;
	
	public GameConfiguration() {
		frames = new ArrayList<FrameConfiguration>();
	}

	public List<FrameConfiguration> getFrames() {
		return frames;
	}

	public void setFrames(List<FrameConfiguration> frames) {
		this.frames = frames;
	}	
}
