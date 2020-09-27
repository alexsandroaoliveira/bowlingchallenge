package org.alexoliveira.bowlingchallenge.domain.models.configuration;

import java.util.ArrayList;
import java.util.List;

public class GameConfiguration {
	
	private int playersNumber;
	
	private List<FrameConfiguration> frames;
	
	public GameConfiguration() {
		playersNumber = 2;
		
		frames = new ArrayList<FrameConfiguration>();
		
		FrameConfiguration frame1 = new FrameConfiguration();
		frame1.setFrameName("1");
		
		ThrowConfiguration shot1 = new ThrowConfiguration();
		shot1.setShotName("Shot1");
		shot1.setMinSchotScore(0);
		shot1.setMaxShotScore(10);
		shot1.setEndFrameScore(10);
		shot1.setFaultScore(0);
		frame1.getFrameShots().add(shot1);

		ThrowConfiguration shot2 = new ThrowConfiguration();
		shot2.setShotName("Shot2");
		shot2.setMinSchotScore(0);
		shot2.setMaxShotScore(10);
		shot2.setFaultScore(0);
		frame1.getFrameShots().add(shot2);
		
		frame1.setScoreRule("Shot1 == 10 : SumScoreFrame() + NextScore(2); Shot2 == 10 : SumScoreFrame() + NextScore(1); SumScoreFrame()");
		
		frames.add(frame1);
		
		FrameConfiguration frame2 = new FrameConfiguration();
		frame2.setFrameName("10");
		
		ThrowConfiguration shot19 = new ThrowConfiguration();
		shot19.setShotName("Shot19");
		shot19.setMinSchotScore(0);
		shot19.setMaxShotScore(10);
		shot19.setFaultScore(0);
		frame2.getFrameShots().add(shot19);

		ThrowConfiguration shot20 = new ThrowConfiguration();
		shot20.setShotName("Shot20");
		shot20.setMinSchotScore(0);
		shot20.setMaxShotScore(10);
		shot20.setFaultScore(0);
		frame2.getFrameShots().add(shot20);
		
		ThrowConfiguration shot21 = new ThrowConfiguration();
		shot21.setShotName("Shot21");
		shot21.setMinSchotScore(0);
		shot21.setMaxShotScore(10);
		shot21.setFaultScore(0);
		frame2.getFrameShots().add(shot21);
		
		frame2.setScoreRule("SumScoreFrame()");
		
		frames.add(frame2);
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
