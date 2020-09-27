package org.alexoliveira.bowlingchallenge.infra;

import org.alexoliveira.bowlingchallenge.domain.models.configuration.FrameConfiguration;
import org.alexoliveira.bowlingchallenge.domain.models.configuration.GameConfiguration;
import org.alexoliveira.bowlingchallenge.domain.models.configuration.ThrowConfiguration;

public class BowlingConfigBuilder implements GameConfigBuilder {

	@Override
	public GameConfiguration build() {
		GameConfiguration gameConfig = new GameConfiguration();
		
		gameConfig.setPlayersNumber(2);
		
		//Frame 1
		FrameConfiguration frame = newFrame("1", 10);
		frame.getFrameShots().add(NewThrow(0, 2, true));
		frame.getFrameShots().add(NewThrow(0, 1, true));
		gameConfig.getFrames().add(frame);
		//Frame 2
		frame = newFrame("2", 10);
		frame.getFrameShots().add(NewThrow(0, 2, true));
		frame.getFrameShots().add(NewThrow(0, 1, true));
		gameConfig.getFrames().add(frame);
		//Frame 3
		frame = newFrame("3", 10);
		frame.getFrameShots().add(NewThrow(0, 2, true));
		frame.getFrameShots().add(NewThrow(0, 1, true));
		gameConfig.getFrames().add(frame);
		//Frame 4
		frame = newFrame("4", 10);
		frame.getFrameShots().add(NewThrow(0, 2, true));
		frame.getFrameShots().add(NewThrow(0, 1, true));
		gameConfig.getFrames().add(frame);
		//Frame 5
		frame = newFrame("5", 10);
		frame.getFrameShots().add(NewThrow(0, 2, true));
		frame.getFrameShots().add(NewThrow(0, 1, true));
		gameConfig.getFrames().add(frame);
		//Frame 6
		frame = newFrame("6", 10);
		frame.getFrameShots().add(NewThrow(0, 2, true));
		frame.getFrameShots().add(NewThrow(0, 1, true));
		gameConfig.getFrames().add(frame);
		//Frame 7
		frame = newFrame("7", 10);
		frame.getFrameShots().add(NewThrow(0, 2, true));
		frame.getFrameShots().add(NewThrow(0, 1, true));
		gameConfig.getFrames().add(frame);
		//Frame 8
		frame = newFrame("8", 10);
		frame.getFrameShots().add(NewThrow(0, 2, true));
		frame.getFrameShots().add(NewThrow(0, 1, true));
		gameConfig.getFrames().add(frame);
		//Frame 9
		frame = newFrame("9", 10);
		frame.getFrameShots().add(NewThrow(0, 2, true));
		frame.getFrameShots().add(NewThrow(0, 1, true));
		gameConfig.getFrames().add(frame);
		//Frame 10
		frame = newFrame("10", 10);
		frame.getFrameShots().add(NewThrow(0, 0, false));
		frame.getFrameShots().add(NewThrow(0, 0, false));
		frame.getFrameShots().add(NewThrow(0, 0, false));
		gameConfig.getFrames().add(frame);
		
		return gameConfig;
	}

	private ThrowConfiguration NewThrow(int faultScore, int pinClearBonus, boolean isEndFrameOnPinClear) {
		ThrowConfiguration shot = new ThrowConfiguration();
		shot.setFaultScore(faultScore);
		shot.setPinClearBonus(pinClearBonus);
		shot.setEndFrameOnPinClear(isEndFrameOnPinClear);
		return shot;
	}

	private FrameConfiguration newFrame(String frameName, int pinNumber) {
		FrameConfiguration frame = new FrameConfiguration();
		frame.setFrameName(frameName);
		frame.setPinNumber(pinNumber);
		
		return frame;
	}
}
