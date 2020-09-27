package org.alexoliveira.bowlingchallenge.domain.models;

import java.util.ArrayList;
import java.util.List;

public class PlayerFrame  {
	
	private int score;
	
	private List<ThrowScore> throwScoreList; 
	
	private int bonus;
	
	public PlayerFrame() {
		throwScoreList = new ArrayList<ThrowScore>();
	}
	
	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public List<ThrowScore> getThrowScoreList() {
		return throwScoreList;
	}

	public void setThrowScoreList(List<ThrowScore> throwScoreList) {
		this.throwScoreList = throwScoreList;
	}

	public int getBonus() {
		return bonus;
	}

	public void setBonus(int bonus) {
		this.bonus = bonus;
	}
}
