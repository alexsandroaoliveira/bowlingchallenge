package org.alexoliveira.bowlingchallenge.domain.models;

import java.util.ArrayList;
import java.util.List;

public class Scoreboard {
	
	private List<ScoreboardPlayer> scoreboardPlayerList;
	
	private List<String> scoreboardFrameList;
	
	public Scoreboard() {
		scoreboardPlayerList = new ArrayList<ScoreboardPlayer>();
		scoreboardFrameList = new ArrayList<String>();
	}
	
	public List<ScoreboardPlayer> getScoreboardPlayerList() {
		return scoreboardPlayerList;
	}

	public void setScoreboardPlayerList(List<ScoreboardPlayer> scoreboardPlayerList) {
		this.scoreboardPlayerList = scoreboardPlayerList;
	}

	public List<String> getScoreboardFrameList() {
		return scoreboardFrameList;
	}

	public void setScoreboardFrameList(List<String> scoreboardFrameList) {
		this.scoreboardFrameList = scoreboardFrameList;
	}

	public class ScoreboardPlayer {
		
		private String name;
		
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		private List<ScoreboardFrame> scoreboardFrameList;
		
		public ScoreboardPlayer() {
			scoreboardFrameList = new ArrayList<ScoreboardFrame>();
		}

		public List<ScoreboardFrame> getScoreboardFrameList() {
			return scoreboardFrameList;
		}

		public void setScoreboardFrameList(List<ScoreboardFrame> scoreboardFrameList) {
			this.scoreboardFrameList = scoreboardFrameList;
		}
	}

	public class ScoreboardFrame {
		
	
		private List<ScoreboardThrow> scoreboardThrowList;
		
		private int score;
		
		private int totalScore;

		
		public ScoreboardFrame() {
			scoreboardThrowList = new ArrayList<ScoreboardThrow>();
			totalScore = 0;
			score = 0;
		}
		
		public List<ScoreboardThrow> getScoreboardThrowList() {
			return scoreboardThrowList;
		}
		
		public void setScoreboardThrowList(List<ScoreboardThrow> scoreboardThrowList) {
			this.scoreboardThrowList = scoreboardThrowList;
		}
		
		public int getScore() {
			return score;
		}
		
		public void setScore(int score) {
			this.score = score;
		}

		public int getTotalScore() {
			return totalScore;
		}

		public void setTotalScore(int totalScore) {
			this.totalScore = totalScore;
		}
		
	}

	public class ScoreboardThrow {
		
		private String pinFalls;

		public String getPinFalls() {
			return pinFalls;
		}

		public void setPinFalls(String pinFalls) {
			this.pinFalls = pinFalls;
		}
	}
}


