package org.alexoliveira.bowlingchallenge.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.alexoliveira.bowlingchallenge.domain.model.Frame;
import org.alexoliveira.bowlingchallenge.domain.model.GameThrow;
import org.alexoliveira.bowlingchallenge.domain.model.Scoreboard;
import org.alexoliveira.bowlingchallenge.domain.model.configuration.GameConfiguration;

public class GameEngine {
	
	private GameConfiguration configuration;
	
	private Map<String, List<String>> scores;
	
	private Frame currentFrame;

	public GameEngine(GameConfiguration configuration) {
	
		this.configuration = configuration;
		
		scores = new HashMap<String, List<String>>();
	
		currentFrame = new Frame();
		currentFrame.setFrameNumber(0);
		currentFrame.setShotNumber(0);
	}

	public void computeNewThrow(GameThrow gt) {
		
		if (!scores.containsKey(gt.getPlayerName())) {
			scores.put(gt.getPlayerName(), new ArrayList<String>());
		}
		
		scores.get(gt.getPlayerName()).add(gt.getScore());	
	}

	public Scoreboard getScoreboard() {
		// TODO Auto-generated method stub
		return null;
	}

}
