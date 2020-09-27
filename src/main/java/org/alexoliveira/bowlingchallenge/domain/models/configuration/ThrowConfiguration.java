package org.alexoliveira.bowlingchallenge.domain.models.configuration;

public class ThrowConfiguration {

	private String shotName;

	private float minSchotScore;

	private float maxShotScore;

	private float EndFrameScore;

	private float faultScore;

	public String getShotName() {
		return shotName;
	}
	
	public void setShotName(String shotName) {
		this.shotName = shotName;
	}
	
	public float getMinSchotScore() {
		return minSchotScore;
	}
	
	public void setMinSchotScore(float minSchotScore) {
		this.minSchotScore = minSchotScore;
	}
	
	public float getMaxShotScore() {
		return maxShotScore;
	}
	
	public void setMaxShotScore(float maxShotScore) {
		this.maxShotScore = maxShotScore;
	}
	
	public float getEndFrameScore() {
		return EndFrameScore;
	}
	
	public void setEndFrameScore(float endFrameScore) {
		EndFrameScore = endFrameScore;
	}
	
	public float getFaultScore() {
		return faultScore;
	}
	
	public void setFaultScore(float faultScore) {
		this.faultScore = faultScore;
	}

}
