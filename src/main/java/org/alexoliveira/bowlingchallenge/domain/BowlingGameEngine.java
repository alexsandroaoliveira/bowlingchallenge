package org.alexoliveira.bowlingchallenge.domain;

import java.util.HashMap;
import java.util.Map;

import org.alexoliveira.bowlingchallenge.domain.interfaces.GameEngine;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerFrame;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrowHistory;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard.ScoreboardFrame;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard.ScoreboardPlayer;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard.ScoreboardThrow;
import org.alexoliveira.bowlingchallenge.domain.models.ThrowScore;
import org.alexoliveira.bowlingchallenge.domain.models.configuration.FrameConfiguration;
import org.alexoliveira.bowlingchallenge.domain.models.configuration.GameConfiguration;
import org.alexoliveira.bowlingchallenge.domain.models.configuration.ThrowConfiguration;
import org.springframework.beans.factory.annotation.Autowired;

public class BowlingGameEngine implements GameEngine {

	@Autowired
	private GameConfiguration configuration;

	private Map<String, PlayerThrowHistory> playersScores;

	private int	frameNumber;
	private int shotNumber;
	private int playerNumber;
	private PlayerFrame playerFrame;

	public BowlingGameEngine() {

		playersScores = new HashMap<String, PlayerThrowHistory>();

		frameNumber = 1;
		shotNumber = 1;
		playerNumber = 1;
		playerFrame = new PlayerFrame();
	}

	public void computeNewThrow(String playerName, String pinfalls) throws Exception {

		if (frameNumber > configuration.getFrames().size()) {
			throw new Exception("Game has ended");
		}

		PlayerThrowHistory playerScore;

		if (!playersScores.containsKey(playerName)) {

			if (playersScores.size() >= configuration.getPlayersNumber()) {
				throw new Exception("Maximum players number reached"+configuration.getPlayersNumber());
			}

			playerScore = new PlayerThrowHistory(playerNumber);
			playersScores.put(playerName, playerScore);

		} else {
			playerScore = playersScores.get(playerName);

		}

		if (playerScore.getPlayerNumber() != playerNumber) {
			throw new Exception("Wrong Player");

		}
		
		FrameConfiguration frameConfig = configuration.getFrames().get(frameNumber-1);
		ThrowConfiguration shotConfig = frameConfig.getFrameShots().get(shotNumber-1);

		if (!playerScore.getFrameList().contains(playerFrame)) {
			playerScore.getFrameList().add(playerFrame);
		}
		
		ThrowScore ts = new ThrowScore();
		ts.setPinfalls(pinfalls);
		
		playerFrame.getThrowScoreList().add(ts);
		playerScore.getShotList().add(ts);

		if (pinfalls.toUpperCase().equals("F")) {
			playerFrame.setScore(playerFrame.getScore() + shotConfig.getFaultScore());
			ts.setScore(shotConfig.getFaultScore());

		} else {
			if (Integer.parseInt(pinfalls) > frameConfig.getPinNumber() || Integer.parseInt(pinfalls) < 0) {
				throw new Exception("Invalid pinfall input: " + pinfalls);
			}
			
			playerFrame.setScore(playerFrame.getScore() + Integer.parseInt(pinfalls));
			ts.setScore(Integer.parseInt(pinfalls));

		}

		if (playerFrame.getScore() >= frameConfig.getPinNumber()) {
			
			if (shotNumber == 1) {
				ts.setPinfalls("X");
			} else {
				ts.setPinfalls("/");
			}
			
			playerFrame.setBonus(shotConfig.getPinClearBonus());
			
			if (shotConfig.isEndFrameOnPinClear()) {

				if (configuration.getPlayersNumber() == playerNumber) {
					frameNumber++;
					playerNumber = 1;
	
				} else {
					playerNumber++;
	
				}
	
				shotNumber = 1;
				playerFrame = new PlayerFrame();
			} else {
				if (shotNumber == frameConfig.getFrameShots().size()) {

					if (playerNumber == configuration.getPlayersNumber()) {
						frameNumber++;
						playerNumber=1;

					}else {
						playerNumber++;

					}

					shotNumber = 1;
					playerFrame = new PlayerFrame();

				} else {
					shotNumber++;

				}
			}

		} else {

			if (shotNumber == frameConfig.getFrameShots().size()) {

				if (playerNumber == configuration.getPlayersNumber()) {
					frameNumber++;
					playerNumber=1;

				}else {
					playerNumber++;

				}

				shotNumber = 1;
				playerFrame = new PlayerFrame();

			} else {
				shotNumber++;

			}
		}
	}

	public Scoreboard getScoreboard() {
		Scoreboard scoreboard = new Scoreboard();

		for (FrameConfiguration frameConfig: configuration.getFrames()) {
			scoreboard.getScoreboardFrameList().add(frameConfig.getFrameName());
		}
		
		for (Map.Entry<String, PlayerThrowHistory> entry : playersScores.entrySet()) {
			ScoreboardPlayer sbp = scoreboard.new ScoreboardPlayer();
			scoreboard.getScoreboardPlayerList().add(sbp);
			sbp.setName(entry.getKey());
			
			System.out.println(entry.getKey());

			PlayerThrowHistory ps = entry.getValue();
			
			for (PlayerFrame pf: ps.getFrameList()) {
				ScoreboardFrame sbf = scoreboard.new ScoreboardFrame();
				sbp.getScoreboardFrameList().add(sbf);

				for (ThrowScore ts: pf.getThrowScoreList()) {
					ScoreboardThrow sbt = scoreboard.new ScoreboardThrow();
					sbf.getScoreboardThrowList().add(sbt);	
					sbt.setPinFalls(ts.getPinfalls());

					System.out.println(ts.getPinfalls()+"-"+ts.getScore());
				}

				int score = pf.getScore();

				for (int i = 1; i <= pf.getBonus(); i++) {
					ThrowScore lastThrowOfFrame = pf.getThrowScoreList().get(pf.getThrowScoreList().size()-1);
					score += ps.getShotList().get(ps.getShotList().indexOf(lastThrowOfFrame) + i).getScore();
				}

				sbf.setScore(score);
				
				System.out.println(pf.getScore()+"-"+score);
			
			}
		}

		return scoreboard;
	}

}
