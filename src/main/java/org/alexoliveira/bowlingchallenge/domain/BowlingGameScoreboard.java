package org.alexoliveira.bowlingchallenge.domain;

import java.util.Map;

import org.alexoliveira.bowlingchallenge.domain.interfaces.GameScoreboard;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.GameConfigReader;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerFrame;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrowHistory;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard;
import org.alexoliveira.bowlingchallenge.domain.models.ThrowScore;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard.ScoreboardFrame;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard.ScoreboardPlayer;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard.ScoreboardThrow;
import org.springframework.beans.factory.annotation.Autowired;

public class BowlingGameScoreboard implements GameScoreboard {
	
	@Autowired
	private GameConfigReader configReader;
	
	@Override
	public Scoreboard getScoreboard(Map<String, PlayerThrowHistory> playersScores) {

		Scoreboard scoreboard = new Scoreboard();

		for (String frameName: configReader.getFrameNames()) {
			scoreboard.getScoreboardFrameList().add(frameName);
		}

		for (Map.Entry<String, PlayerThrowHistory> entry : playersScores.entrySet()) {
			ScoreboardPlayer sbp = scoreboard.new ScoreboardPlayer();
			scoreboard.getScoreboardPlayerList().add(sbp);
			sbp.setName(entry.getKey());

			PlayerThrowHistory ps = entry.getValue();
			
			int totalScore = 0;
			for (int frameNumber = 1; frameNumber <= configReader.getFrameNumber(); frameNumber++) {

				PlayerFrame pf = ps.getFrameList().get(frameNumber - 1);

				ScoreboardFrame sbf = scoreboard.new ScoreboardFrame();
				sbp.getScoreboardFrameList().add(sbf);


				for (int i = pf.getThrowScoreList().size(); i < configReader.getTrowsNumber(frameNumber); i++) {
					ScoreboardThrow sbt = scoreboard.new ScoreboardThrow();
					sbf.getScoreboardThrowList().add(sbt);	
					sbt.setPinFalls("");
				}
				
				for (ThrowScore ts: pf.getThrowScoreList()) {
					ScoreboardThrow sbt = scoreboard.new ScoreboardThrow();
					sbf.getScoreboardThrowList().add(sbt);	
					sbt.setPinFalls(ts.getPinfalls());
				}

				int score = pf.getScore();

				for (int i = 1; i <= pf.getBonus(); i++) {
					ThrowScore lastThrowOfFrame = pf.getThrowScoreList().get(pf.getThrowScoreList().size()-1);
					score += ps.getShotList().get(ps.getShotList().indexOf(lastThrowOfFrame) + i).getScore();
				}

				sbf.setScore(score);
				totalScore += score;
				sbf.setTotalScore(totalScore);
			}
		}

		return scoreboard;
	}
}
