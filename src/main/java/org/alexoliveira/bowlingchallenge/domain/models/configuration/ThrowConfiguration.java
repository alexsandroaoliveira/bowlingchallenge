package org.alexoliveira.bowlingchallenge.domain.models.configuration;

public class ThrowConfiguration {

	private int faultScore;
	
	private int pinClearBonus;
	
	private boolean endFrameOnPinClear;

	public int getFaultScore() {
		return faultScore;
	}

	public void setFaultScore(int faultScore) {
		this.faultScore = faultScore;
	}

	public int getPinClearBonus() {
		return pinClearBonus;
	}

	public void setPinClearBonus(int pinClearBonus) {
		this.pinClearBonus = pinClearBonus;
	}

	public boolean isEndFrameOnPinClear() {
		return endFrameOnPinClear;
	}

	public void setEndFrameOnPinClear(boolean endFrameOnPinClear) {
		this.endFrameOnPinClear = endFrameOnPinClear;
	}
}
