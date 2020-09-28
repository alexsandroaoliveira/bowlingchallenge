package org.alexoliveira.bowlingchallenge.domain.interfaces;


public interface GameValidation {

	void validateTurn(int playerTurnNumber, int currentFrameNumber, int currentPlayerNumber) throws Exception;
	
	void validadeThrowScore(int score, int currentFrameNumber) throws Exception;
}
