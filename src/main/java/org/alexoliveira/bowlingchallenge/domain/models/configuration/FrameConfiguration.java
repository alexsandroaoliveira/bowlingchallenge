package org.alexoliveira.bowlingchallenge.domain.models.configuration;

import java.util.ArrayList;
import java.util.List;

public class FrameConfiguration {

	private String frameName;
	
	private int pinNumber;
	
	private List<ThrowConfiguration> frameShots;
	
	public FrameConfiguration() {
		frameShots= new ArrayList<ThrowConfiguration>();	
	}

	public String getFrameName() {
		return frameName;
	}

	public void setFrameName(String frameName) {
		this.frameName = frameName;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

	public List<ThrowConfiguration> getFrameShots() {
		return frameShots;
	}

	public void setFrameShots(List<ThrowConfiguration> frameShots) {
		this.frameShots = frameShots;
	}

}
