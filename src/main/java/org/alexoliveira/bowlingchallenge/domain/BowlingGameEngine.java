package org.alexoliveira.bowlingchallenge.domain;

import org.alexoliveira.bowlingchallenge.domain.interfaces.GameEngine;
import org.alexoliveira.bowlingchallenge.domain.interfaces.GameValidation;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.GameConfigReader;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.PlayerThrowHistoryRepository;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerFrame;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrowHistory;
import org.alexoliveira.bowlingchallenge.domain.models.ThrowScore;
import org.springframework.beans.factory.annotation.Autowired;

public class BowlingGameEngine implements GameEngine {

	@Autowired
	private GameConfigReader configReader;
	
	@Autowired
	private GameValidation validation;
	
	@Autowired
	private PlayerThrowHistoryRepository playerThrowHistoryRepository;


	private int	currentFrameNumber;
	private int currentShotNumber;
	private int currentPlayerNumber;
	private int currentScore;

	private PlayerFrame currentFrame;

	public BowlingGameEngine() {
		currentFrameNumber = 1;
		currentShotNumber = 1;
		currentPlayerNumber = 1;
		currentScore = 0;
		currentFrame = new PlayerFrame(currentFrameNumber);
	}

	@Override
	public void computeNewThrow(String playerName, String pinfalls) throws Exception {

		PlayerThrowHistory playerThrowHistory;

		if (playerThrowHistoryRepository.isPlayerExists(0, playerName)) {
			playerThrowHistory = playerThrowHistoryRepository.get(0, playerName);

		} else {
			playerThrowHistory = newPlayer(playerName);

		}

		validation.validateTurn(playerThrowHistory.getPlayerNumber(), currentFrameNumber, currentPlayerNumber);

		//addCurrentFrameToPlayerHist - Using Stream and Lambda
		if (!playerThrowHistory.getFrameList().stream().anyMatch(n -> n.getFrameNumber() == currentFrameNumber)) {
			playerThrowHistory.getFrameList().add(currentFrame);
		}

		int numberOfPins = configReader.getNumberOfPins(currentFrameNumber);
		
		setThrowScore(pinfalls, numberOfPins, playerThrowHistory);

		boolean isEndFrameOnPinClear = configReader.isEndFrameOnPinClear(currentFrameNumber, currentShotNumber);
		moveToNextTurn(isEndFrameOnPinClear, numberOfPins);
	}

	private PlayerThrowHistory newPlayer(String playerName) throws Exception {
		
		PlayerThrowHistory playerThrowHistory;
		
		if (playerThrowHistoryRepository.getPlayers(0).size() >= configReader.getNumberOfPlayers()) {
			throw new Exception("Maximum players number reached" + configReader.getNumberOfPlayers());
		}

		playerThrowHistory = new PlayerThrowHistory(currentPlayerNumber);
		playerThrowHistoryRepository.insert(0, playerName, playerThrowHistory);
		
		return playerThrowHistory;
	}
	
	private void setThrowScore(String pinfalls, int numberOfPins,
			PlayerThrowHistory playerThrowHistory) throws Exception {
		
		int pinClearBonus = configReader.getPinClearBonus(currentFrameNumber, currentShotNumber);
		
		ThrowScore ts = new ThrowScore();
		ts.setPinfalls(pinfalls);

		currentFrame.getThrowScoreList().add(ts);
		playerThrowHistory.getThrowList().add(ts);

		int throwScore = getThrowScore(pinfalls);
		currentScore += throwScore;
		ts.setScore(throwScore);

		//Check if Strike or Spare
		if (currentScore == numberOfPins) {

			ts.setPinfalls(getStrikeSpareMark());			

			currentFrame.setScore(currentFrame.getScore() + currentScore);

			currentFrame.setBonus(pinClearBonus);
		}
	}

	private void moveToNextTurn(boolean isEndFrameOnPinClear, int numberOfPins) {
		if (currentScore == numberOfPins) {
			currentScore=0;

			if (isEndFrameOnPinClear) {
				moveToNextFrame();

			} else {
				moveToNextThrow();

			}

		} else {
			moveToNextThrow();

		}
	}

	private void moveToNextThrow() {
		if (currentShotNumber == configReader.getTrowsNumber(currentFrameNumber)) {
			currentFrame.setScore(currentFrame.getScore() + currentScore);
			currentScore = 0;

			moveToNextFrame();

		} else {
			currentShotNumber++;

		}
	}

	private void moveToNextFrame() {
		if (currentPlayerNumber == configReader.getNumberOfPlayers()) {
			currentFrameNumber++;
			currentPlayerNumber=1;

		}else {
			currentPlayerNumber++;

		}

		currentShotNumber = 1;
		currentFrame = new PlayerFrame(currentFrameNumber);
	}

	private String getStrikeSpareMark() {
		if (currentShotNumber == 1) {
			return "X";
		} else if (!configReader.isEndFrameOnPinClear(currentFrameNumber, currentShotNumber))
		{
			return "X";
		} else {
			return "/";
		}
	}

	private int getThrowScore(String pinfalls) throws Exception {
		int throwScore;
		if (pinfalls.toUpperCase().equals("F")) {
			throwScore = configReader.getFaultScore(currentFrameNumber, currentShotNumber);

		} else {
			throwScore = Integer.parseInt(pinfalls);
		}

		validation.validadeThrowScore(throwScore, currentFrameNumber);
		
		return throwScore;
	}

}