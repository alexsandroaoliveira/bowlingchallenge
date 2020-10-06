package org.alexsandrooliveira.bowlingchallenge.unittest;

import org.alexoliveira.bowlingchallenge.domain.interfaces.GameEngine;
import org.alexoliveira.bowlingchallenge.domain.interfaces.GameValidation;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.GameConfigReader;
import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.PlayerThrowHistoryRepository;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrowHistory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import static org.mockito.Mockito.*;

import java.util.Map;

public class BowlingGameEngineTest extends TestCase {

	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public BowlingGameEngineTest( String testName )
	{
		super( testName );
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite()
	{
		return new TestSuite( BowlingGameEngineTest.class );
	}

	/**
	 * Rigourous Test :-)
	 * @throws Exception 
	 */
	public void testComputeNewThrow() throws Exception
	{
		try (AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfigTest.class)) {

			GameValidation mockedGameValidation = context.getBean(GameValidation.class);			
			GameConfigReader mockedConfigReader = context.getBean(GameConfigReader.class);			
			
			when(mockedConfigReader.getNumberOfFrames()).thenReturn(2);
			when(mockedConfigReader.getTrowsNumber(1)).thenReturn(2);
			when(mockedConfigReader.getTrowsNumber(2)).thenReturn(2);
			when(mockedConfigReader.getNumberOfPins(1)).thenReturn(2);
			when(mockedConfigReader.getNumberOfPins(2)).thenReturn(2);
			when(mockedConfigReader.isEndFrameOnPinClear(1, 1)).thenReturn(true);
			when(mockedConfigReader.isEndFrameOnPinClear(1, 2)).thenReturn(true);
			when(mockedConfigReader.isEndFrameOnPinClear(2, 1)).thenReturn(true);
			when(mockedConfigReader.isEndFrameOnPinClear(2, 2)).thenReturn(true);
			
			GameEngine gameEngine = context.getBean(GameEngine.class);
			gameEngine.computeNewThrow("p1", "2");
			gameEngine.computeNewThrow("p2", "1");
			gameEngine.computeNewThrow("p2", "1");
			gameEngine.computeNewThrow("p1", "1");
			gameEngine.computeNewThrow("p1", "0");
			gameEngine.computeNewThrow("p2", "2");
			

			PlayerThrowHistoryRepository playerThrowHistoryRepository = context.getBean(PlayerThrowHistoryRepository.class);
			Map<String, PlayerThrowHistory> score = playerThrowHistoryRepository.getAll();

			verify(mockedGameValidation).validateTurn(1, 1, 1);
			verify(mockedGameValidation).validadeThrowScore(2, 1);
			verify(mockedGameValidation, times(2)).validateTurn(2, 1, 2);
			verify(mockedGameValidation, times(2)).validadeThrowScore(1, 1);
			
			assertTrue( score.size() == 2 );
			
			PlayerThrowHistory p1 = score.get("p1");
			assertTrue( p1.getThrowList().size() == 3 );
			assertTrue( p1.getFrameList().size() == 2 );
			assertTrue( p1.getFrameList().get(0).getScore() == 2 );
			assertTrue( p1.getFrameList().get(1).getScore() == 1 );
			
			PlayerThrowHistory p2 = score.get("p2");
			assertTrue( p2.getThrowList().size() == 3 );
			assertTrue( p2.getFrameList().size() == 2 );
			assertTrue( p2.getFrameList().get(0).getScore() == 2 );
			assertTrue( p2.getFrameList().get(1).getScore() == 2 );
		}
	}
}
