package org.alexsandrooliveira.bowlingchallenge.integrationtest;

import java.util.List;

import org.alexoliveira.bowlingchallenge.application.AppConfig;
import org.alexoliveira.bowlingchallenge.domain.interfaces.Game;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.GameFileReader;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrow;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard;
import org.alexoliveira.bowlingchallenge.domain.models.Scoreboard.ScoreboardFrame;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BowlingGameTest extends TestCase {

	public BowlingGameTest( String testName ) {
		super( testName );
	}

	public static Test suite() {
		return new TestSuite( BowlingGameTest.class );
	}

	public void testSampleGame() {
		try (AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {

			Scoreboard scoreboard = runGame(context, "game-score-sample.txt");

			assertNotNull(scoreboard);			
			
			List<ScoreboardFrame> p1 = scoreboard.getScoreboardPlayerList().get(0).getScoreboardFrameList();
			assertEquals(167, p1.get(p1.size()-1).getTotalScore());

			List<ScoreboardFrame> p2 = scoreboard.getScoreboardPlayerList().get(1).getScoreboardFrameList();
			assertEquals(151, p2.get(p1.size()-1).getTotalScore());

		}
	}
	
	public void testPerfectGame() {
		try (AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {

			Scoreboard scoreboard = runGame(context, "game-score-perfect.txt");

			assertNotNull(scoreboard);			
			
			List<ScoreboardFrame> p1 = scoreboard.getScoreboardPlayerList().get(0).getScoreboardFrameList();
			assertEquals(300, p1.get(p1.size()-1).getTotalScore());

			List<ScoreboardFrame> p2 = scoreboard.getScoreboardPlayerList().get(1).getScoreboardFrameList();
			assertEquals(300, p2.get(p1.size()-1).getTotalScore());

		}
	}
	
	
	public void testZeroGame() {
		try (AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)) {

			Scoreboard scoreboard = runGame(context, "game-score-zero.txt");

			assertNotNull(scoreboard);			
			
			List<ScoreboardFrame> p1 = scoreboard.getScoreboardPlayerList().get(0).getScoreboardFrameList();
			assertEquals(0, p1.get(p1.size()-1).getTotalScore());

			List<ScoreboardFrame> p2 = scoreboard.getScoreboardPlayerList().get(1).getScoreboardFrameList();
			assertEquals(0, p2.get(p1.size()-1).getTotalScore());

		}
	}

	private Scoreboard runGame(AbstractApplicationContext context, String gameFile) {
		GameFileReader reader = context.getBean(GameFileReader.class);
		Game game = context.getBean(Game.class);
		Scoreboard scoreboard = null;

		try {

			List<PlayerThrow> throwList = reader.readFile(gameFile);

			for (PlayerThrow playerThrow: throwList) {

				game.computeNewThrow(playerThrow.getPlayerName(), playerThrow.getPinfalls());

			}

			scoreboard = game.getScoreboard();

		} catch (Exception e) {
			e.printStackTrace();

		}
		return scoreboard;
	}
}