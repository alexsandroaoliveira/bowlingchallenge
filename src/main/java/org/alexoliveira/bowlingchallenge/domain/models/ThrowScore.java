package org.alexoliveira.bowlingchallenge.domain.models;

public class ThrowScore {
	
	private String pinfalls;
	
	private int score;
	
	public ThrowScore() {
		score = 0;
	}

	public String getPinfalls() {
		return pinfalls;
	}

	public void setPinfalls(String pinfalls) {
		this.pinfalls = pinfalls;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}	
}
