package org.alexoliveira.bowlingchallenge.domain.interfaces.infra;

import java.util.List;

public interface GameConfigReader {

	int getNumberOfFrames();

	int getNumberOfPlayers();

	List<String> getFrameNames();

	int getTrowsNumber(int frameNumber);

	int getFaultScore(int frameNumber, int shotNumber);

	int getNumberOfPins(int frameNumber);

	boolean isEndFrameOnPinClear(int frameNumber, int shotNumber);

	int getPinClearBonus(int frameNumber, int shotNumber);

}
