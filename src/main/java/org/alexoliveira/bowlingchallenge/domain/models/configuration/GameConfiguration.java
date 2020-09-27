package org.alexoliveira.bowlingchallenge.domain.models.configuration;

import java.util.ArrayList;
import java.util.List;

public class GameConfiguration {
	
	private int playersNumber;
	
	private List<FrameConfiguration> frames;
	
	public GameConfiguration() {
		playersNumber = 2;
		
		frames = new ArrayList<FrameConfiguration>();
		
		FrameConfiguration frame = new FrameConfiguration();
		frame.setFrameName("1");
		frame.setPinNumber(10);
		
		ThrowConfiguration shot = new ThrowConfiguration();
		shot.setFaultScore(0);
		shot.setPinClearBonus(2);
		shot.setEndFrameOnPinClear(true);
		frame.getFrameShots().add(shot);

		shot = new ThrowConfiguration();
		shot.setFaultScore(0);
		shot.setPinClearBonus(1);
		shot.setEndFrameOnPinClear(true);
		frame.getFrameShots().add(shot);
	
		frames.add(frame);
		
		frame = new FrameConfiguration();
		frame.setFrameName("2");
		frame.setPinNumber(10);
		
		shot = new ThrowConfiguration();
		shot.setFaultScore(0);
		shot.setPinClearBonus(0);
		shot.setEndFrameOnPinClear(false);
		frame.getFrameShots().add(shot);

		shot = new ThrowConfiguration();
		shot.setFaultScore(0);
		shot.setPinClearBonus(0);
		shot.setEndFrameOnPinClear(false);
		frame.getFrameShots().add(shot);
		
		shot = new ThrowConfiguration();
		shot.setFaultScore(0);
		shot.setPinClearBonus(0);
		shot.setEndFrameOnPinClear(false);
		frame.getFrameShots().add(shot);
		
		frames.add(frame);
	}
	
	public int getPlayersNumber() {
		return playersNumber;
	}

	public void setPlayersNumber(int playersNumber) {
		this.playersNumber = playersNumber;
	}

	public List<FrameConfiguration> getFrames() {
		return frames;
	}

	public void setFrames(List<FrameConfiguration> frames) {
		this.frames = frames;
	}

}
