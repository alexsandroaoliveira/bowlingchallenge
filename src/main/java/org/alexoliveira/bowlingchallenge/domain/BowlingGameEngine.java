package org.alexoliveira.bowlingchallenge.domain;

import java.util.HashMap;
import java.util.Map;

import org.alexoliveira.bowlingchallenge.domain.interfaces.GameEngine;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrowHistory;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard.ScoreboardFrame;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard.ScoreboardPlayer;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard.ScoreboardThrow;
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

	public BowlingGameEngine() {

		playersScores = new HashMap<String, PlayerThrowHistory>();

		frameNumber = 1;
		shotNumber = 1;
		playerNumber = 1;
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

		playerScore.getShots().add(pinfalls);

		float score = 0;

		if (pinfalls.toUpperCase().equals("F")) {
			score = configuration.getFrames().get(frameNumber-1).getFrameShots().get(shotNumber-1).getFaultScore();

		} else {
			score = Float.parseFloat(pinfalls);

		}

		float endFrameScore = configuration.getFrames().get(frameNumber-1).getFrameShots().get(shotNumber-1).getEndFrameScore();

		if (score >= endFrameScore && endFrameScore > 0) {

			for (int i = frameNumber; i < configuration.getFrames().get(frameNumber-1).getFrameShots().size(); i++)
			{
				playersScores.get(playerName).getShots().add("B");

			}

			if (configuration.getPlayersNumber() == playerNumber) {
				frameNumber++;
				playerNumber = 1;

			} else {
				playerNumber++;

			}

			shotNumber = 1;

		} else {

			if (shotNumber == configuration.getFrames().get(frameNumber-1).getFrameShots().size()) {

				if (playerNumber == configuration.getPlayersNumber()) {
					frameNumber++;
					playerNumber=1;

				}else {
					playerNumber++;

				}

				shotNumber=1;

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

			PlayerThrowHistory ps = entry.getValue();
			int boardFrameNum = 1;
			int boardThrowNum = 1;
			int i = 0;

			FrameConfiguration frameConfig = configuration.getFrames().get(boardFrameNum-1);
			ThrowConfiguration shotConfig = frameConfig.getFrameShots().get(boardThrowNum-1);

			ScoreboardFrame sbf = scoreboard.new ScoreboardFrame();
			sbp.getScoreboardFrameList().add(sbf);
			float frameScore = 0; 

			while (boardFrameNum <= configuration.getFrames().size()) {
				
				if (boardThrowNum <= frameConfig.getFrameShots().size()) {
					shotConfig = frameConfig.getFrameShots().get(boardThrowNum-1);
					
					ScoreboardThrow sbt = scoreboard.new ScoreboardThrow();
					sbt.setPinFalls(ps.getShots().get(i));
					sbf.getScoreboardThrowList().add(sbt);
					
				}  
				
				if (i < ps.getShots().size()) {
				
					if (ps.getShots().get(i).toUpperCase().equals("F")) {
						frameScore += shotConfig.getFaultScore();
						
					} else if (ps.getShots().get(i).toUpperCase().equals("B")) {
						frameScore += 0;
						
					} else {
						frameScore += Float.parseFloat(ps.getShots().get(i));
						
					}
				}
				
				boardThrowNum++;
				
				if (boardThrowNum > frameConfig.getFrameShots().size()) {

					sbf.setScore(frameScore);
					
					boardFrameNum++;
					boardThrowNum = 1;

					if (boardFrameNum > configuration.getFrames().size()) {
						break;
					}

					frameConfig = configuration.getFrames().get(boardFrameNum-1);

					sbf = scoreboard.new ScoreboardFrame();
					sbp.getScoreboardFrameList().add(sbf);					
				}

				i++;
			}
	
		}

		return scoreboard;
	}

}
