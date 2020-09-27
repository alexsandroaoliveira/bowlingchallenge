package org.alexoliveira.bowlingchallenge.infra;

import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.ScoreRender;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard.ScoreboardFrame;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard.ScoreboardPlayer;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard.ScoreboardThrow;

public class BowlingScoreRender implements ScoreRender {

	public void render(Scoreboard scoreboard) {
		//header
		String headStr = "Frame";
		for (String frameName: scoreboard.getScoreboardFrameList()) {
			headStr += "\t\t" + frameName ;
		}
		
		System.out.println(headStr);
		
		//Players
		for (ScoreboardPlayer sbp: scoreboard.getScoreboardPlayerList()) {
			
			//Name
			System.out.println(sbp.getName());
			
			//Pinfalls
			String pitfallsStr = "Pitfalls\t";
			String scoreStr = "Score";

			for (ScoreboardFrame sbf: sbp.getScoreboardFrameList()) {
				for (ScoreboardThrow sbt: sbf.getScoreboardThrowList()) {
					pitfallsStr += sbt.getPinFalls() + "\t";
				}
				
				scoreStr += "\t\t"+sbf.getTotalScore();
			}
			System.out.println(pitfallsStr);
			
			//Score
			System.out.println(scoreStr);
			
		}
	}

}
