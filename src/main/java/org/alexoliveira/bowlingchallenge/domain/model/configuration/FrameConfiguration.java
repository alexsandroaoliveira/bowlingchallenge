package org.alexoliveira.bowlingchallenge.domain.model.configuration;

import java.util.ArrayList;
import java.util.List;

public class FrameConfiguration {
	
	private String frameName;
	
	private List<ShotConfiguration> frameShots;
	
	private String scoreRule;
	
	public FrameConfiguration() {
		frameShots = new ArrayList<ShotConfiguration>();
	}
	
	public List<ShotConfiguration> getFrameShots() {
		return frameShots;
	}
	
	public void setFrameShots(List<ShotConfiguration> frameShots) {
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
