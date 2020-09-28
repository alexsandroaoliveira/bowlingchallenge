package org.alexoliveira.bowlingchallenge.infra;

import java.util.ArrayList;
import java.util.List;

import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.GameConfigReader;
import org.alexoliveira.bowlingchallenge.domain.models.configuration.FrameConfiguration;
import org.alexoliveira.bowlingchallenge.domain.models.configuration.GameConfiguration;
import org.alexoliveira.bowlingchallenge.domain.models.configuration.ThrowConfiguration;

public class BowlingConfigReader implements GameConfigReader {

	private GameConfiguration configuration;

	public BowlingConfigReader() {
		GameConfigBuilder gameConfigBuilder = new BowlingConfigBuilder();
		configuration = gameConfigBuilder.build();
	}

	@Override
	public int getNumberOfFrames() {
		return configuration.getFrames().size();
	}

	@Override
	public int getNumberOfPlayers() {
		return configuration.getPlayersNumber();
	}

	@Override
	public List<String> getFrameNames() {
		List<String> frameNames = new ArrayList<>();
		
		for (FrameConfiguration frameConfig: configuration.getFrames()) {
			frameNames.add(frameConfig.getFrameName());
		}
		return frameNames;
	}

	@Override
	public int getTrowsNumber(int frameNumber) {
		return getFrame(frameNumber).getFrameShots().size();
	}

	@Override
	public int getFaultScore(int frameNumber, int shotNumber) {
		return getThrowConfig(frameNumber, shotNumber).getFaultScore();
		
	}
	
	@Override
	public int getNumberOfPins(int frameNumber) {
		return getFrame(frameNumber).getPinNumber();
	}
	
	@Override
	public boolean isEndFrameOnPinClear(int frameNumber, int shotNumber) {
		return getThrowConfig(frameNumber, shotNumber).isEndFrameOnPinClear();
	}

	@Override
	public int getPinClearBonus(int frameNumber, int shotNumber) {
		return getThrowConfig(frameNumber, shotNumber).getPinClearBonus();
	}
	
	private FrameConfiguration getFrame(int frameNumber) {
		return configuration.getFrames().get(frameNumber-1);
	}

	private ThrowConfiguration getThrowConfig(int frameNumber, int shotNumber) {
		
		FrameConfiguration frameConfig = getFrame(frameNumber);
		
		return frameConfig.getFrameShots().get(shotNumber -1);
	}


}
