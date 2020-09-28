package org.alexoliveira.bowlingchallenge.domain;

import org.alexoliveira.bowlingchallenge.domain.interfaces.GameValidation;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.GameConfigReader;
import org.springframework.beans.factory.annotation.Autowired;

public class BowlingGameValidation implements GameValidation {

	@Autowired
	private GameConfigReader configReader;

	
	@Override
	public void validateTurn(int playerTurnNumber, int currentFrameNumber, int currentPlayerNumber) throws Exception {
		validateIfGameOver(currentFrameNumber); 
		validatePlayerTurn(playerTurnNumber, currentPlayerNumber);
		
	}
	
	@Override
	public void validadeThrowScore(int score, int currentFrameNumber) throws Exception {
		if (score > configReader.getNumberOfPins(currentFrameNumber) ||score < 0) {
			throw new Exception("Invalid pinfall input: " + score);
		}
	}
	
	private void validateIfGameOver(int currentFrameNumber) throws Exception {
		if (currentFrameNumber > configReader.getNumberOfFrames()) {
			throw new Exception("The game is over");
		}
	}
	
	private void validatePlayerTurn(int playerTurnNumber, int currentPlayerNumber) throws Exception {
		if (playerTurnNumber != currentPlayerNumber) {
			throw new Exception("Wrong Player");
		}
	}
	
	

}
