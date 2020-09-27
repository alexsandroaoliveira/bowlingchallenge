package org.alexoliveira.bowlingchallenge.domain;

import java.util.HashMap;
import java.util.Map;

import org.alexoliveira.bowlingchallenge.domain.interfaces.GameEngine;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.GameConfigReader;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerFrame;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrowHistory;
import org.alexoliveira.bowlingchallenge.domain.models.ThrowScore;
import org.springframework.beans.factory.annotation.Autowired;

public class BowlingGameEngine implements GameEngine {

	@Autowired
	private GameConfigReader configReader;

	private Map<String, PlayerThrowHistory> gameThrowHistory;

	private int	currentFrameNumber;
	private int currentShotNumber;
	private int currentPlayerNumber;
	private int currentScore;

	private PlayerFrame currentFrame;

	public BowlingGameEngine() {
		gameThrowHistory = new HashMap<String, PlayerThrowHistory>();
	
		currentFrameNumber = 1;
		currentShotNumber = 1;
		currentPlayerNumber = 1;
		currentScore = 0;
		currentFrame = new PlayerFrame();
	}
	
	@Override
	public void computeNewThrow(String playerName, String pinfalls) throws Exception {
		
		if (currentFrameNumber > configReader.getFrameNumber()) {
			throw new Exception("The game is over");
		}

		PlayerThrowHistory playerThrowHistory;

		if (!gameThrowHistory.containsKey(playerName)) {

			if (gameThrowHistory.size() >= configReader.getPlayersNumber()) {
				throw new Exception("Maximum players number reached" + configReader.getPlayersNumber());
			}

			playerThrowHistory = new PlayerThrowHistory(currentPlayerNumber);
			gameThrowHistory.put(playerName, playerThrowHistory);

		} else {
			playerThrowHistory = gameThrowHistory.get(playerName);

		}

		if (playerThrowHistory.getPlayerNumber() != currentPlayerNumber) {
			throw new Exception("Wrong Player");

		}

		if (!playerThrowHistory.getFrameList().contains(currentFrame)) {
			playerThrowHistory.getFrameList().add(currentFrame);
		}

		ThrowScore ts = new ThrowScore();
		ts.setPinfalls(pinfalls);

		currentFrame.getThrowScoreList().add(ts);
		playerThrowHistory.getShotList().add(ts);

		if (pinfalls.toUpperCase().equals("F")) {
			int faultScore = configReader.getFaultScore(currentFrameNumber, currentShotNumber);
			currentScore += faultScore;
			ts.setScore(faultScore);

		} else {
			if (Integer.parseInt(pinfalls) > configReader.getNumberOfPins(currentFrameNumber) || Integer.parseInt(pinfalls) < 0) {
				throw new Exception("Invalid pinfall input: " + pinfalls);
			}

			currentScore += Integer.parseInt(pinfalls);
			ts.setScore(Integer.parseInt(pinfalls));

		}

		if (currentScore >= configReader.getNumberOfPins(currentFrameNumber)) {

			currentFrame.setScore(currentFrame.getScore() + currentScore);
			currentScore=0;

			if (currentShotNumber == 1) {
				ts.setPinfalls("X");
			} else if (!configReader.isEndFrameOnPinClear(currentFrameNumber, currentShotNumber))
			{
				ts.setPinfalls("X");
			} else {
				ts.setPinfalls("/");
			}


			currentFrame.setBonus(configReader.getPinClearBonus(currentFrameNumber, currentShotNumber));

			if (configReader.isEndFrameOnPinClear(currentFrameNumber, currentShotNumber)) {

				if (configReader.getPlayersNumber() == currentPlayerNumber) {
					currentFrameNumber++;
					currentPlayerNumber = 1;

				} else {
					currentPlayerNumber++;

				}

				currentShotNumber = 1;
				currentFrame = new PlayerFrame();
			} else {
				if (currentShotNumber == configReader.getTrowsNumber(currentFrameNumber)) {

					if (currentPlayerNumber == configReader.getPlayersNumber()) {
						currentFrameNumber++;
						currentPlayerNumber=1;

					}else {
						currentPlayerNumber++;

					}

					currentShotNumber = 1;
					currentFrame = new PlayerFrame();

				} else {
					currentShotNumber++;

				}
			}

		} else {

			if (currentShotNumber == configReader.getTrowsNumber(currentFrameNumber)) {
				currentFrame.setScore(currentFrame.getScore() + currentScore);
				currentScore = 0;

				if (currentPlayerNumber == configReader.getPlayersNumber()) {
					currentFrameNumber++;
					currentPlayerNumber=1;

				}else {
					currentPlayerNumber++;

				}

				currentShotNumber = 1;
				currentFrame = new PlayerFrame();

			} else {
				currentShotNumber++;

			}
		}
	}
	
	@Override
	public Map<String, PlayerThrowHistory> getPlayersScores() {
		return gameThrowHistory;
	}
}
