package org.alexoliveira.bowlingchallenge.domain.models.configuration;

import java.util.ArrayList;
import java.util.List;

public class FrameConfiguration {
	
	private String frameName;
	
	private List<ThrowConfiguration> frameShots;
	
	private String scoreRule;
	
	public FrameConfiguration() {
		frameShots = new ArrayList<ThrowConfiguration>();
	}
	
	public List<ThrowConfiguration> getFrameShots() {
		return frameShots;
	}
	
	public void setFrameShots(List<ThrowConfiguration> frameShots) {
		this.frameShots = frameShots;
	}
	
	public String getFrameName() {
		return frameName;
	}
	
	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}

	public String getScoreRule() {
		return scoreRule;
	}

	public void setScoreRule(String scoreRule) {
		this.scoreRule = scoreRule;
	}
	
	

}
