package org.alexoliveira.bowlingchallenge.domain;

import java.util.HashMap;
import java.util.Map;

import org.alexoliveira.bowlingchallenge.domain.model.PlayerScore;
import org.alexoliveira.bowlingchallenge.domain.model.Scoreboard;
import org.alexoliveira.bowlingchallenge.domain.model.configuration.GameConfiguration;

public class GameEngine {

	private GameConfiguration configuration;

	private Map<String, PlayerScore> playersScores;

	private int	frameNumber;
	private int shotNumber;
	private int playerNumber;

	public GameEngine(GameConfiguration configuration) {

		this.configuration = configuration;

		playersScores = new HashMap<String, PlayerScore>();

		frameNumber = 1;
		shotNumber = 1;
		playerNumber = 1;
	}

	public void computeNewThrow(String playerName, String pinfalls) throws Exception {

		PlayerScore playerScore;

		if (!playersScores.containsKey(playerName)) {

			if (playersScores.size() >= configuration.getPlayersNumber()) {
				throw new Exception("Maximum players number reached"+configuration.getPlayersNumber());
			}

			playerScore = new PlayerScore(playerNumber);
			playersScores.put(playerName, playerScore);

		} else {
			playerScore = playersScores.get(playerName);
			
		}

		if (playerScore.getPlayerNumber() != playerNumber) {
			throw new Exception("Wrong Player");
			
		}

		playerScore.getShots().add(pinfalls);

		float score = 0;

		if (pinfalls.toUpperCase().equals("F")) {
			score = configuration.getFrames().get(frameNumber-1).getFrameShots().get(shotNumber-1).getFaultScore();

		} else {
			score = Float.parseFloat(pinfalls);

		}

		float endFrameScore = configuration.getFrames().get(frameNumber-1).getFrameShots().get(shotNumber-1).getEndFrameScore();

		if (score >= endFrameScore && endFrameScore > 0) {

			for (int i = frameNumber; i < configuration.getFrames().get(frameNumber-1).getFrameShots().size(); i++)
			{
				playersScores.get(playerName).getShots().add("B");

			}

			if (configuration.getPlayersNumber() == playerNumber) {
				frameNumber++;
				playerNumber = 1;

			} else {
				playerNumber++;

			}

			shotNumber = 1;

		} else {

			if (shotNumber == configuration.getFrames().get(frameNumber-1).getFrameShots().size()) {

				if (playerNumber == configuration.getPlayersNumber()) {
					frameNumber++;
					playerNumber=1;

				}else {
					playerNumber++;

				}

				shotNumber=1;

			} else {
				shotNumber++;

			}
		}
		
		//TODO - Remover
		getScoreboard();
	}

	public Scoreboard getScoreboard() {
	    //TODO - Remover
		for (Map.Entry<String, PlayerScore> entry : playersScores.entrySet()) {
			StringBuilder sb = new StringBuilder(entry.getKey()+": ");
			
			for (String pinfalls: entry.getValue().getShots()) {
				sb.append(pinfalls+", ");
			}

			System.out.println(sb);
	    }

		Scoreboard scoreboard = new Scoreboard();

		return scoreboard;
	}

}
