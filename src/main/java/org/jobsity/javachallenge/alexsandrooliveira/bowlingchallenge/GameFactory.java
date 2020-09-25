package org.jobsity.javachallenge.alexsandrooliveira.bowlingchallenge;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.jobsity.javachallenge.alexsandrooliveira.bowlingchallenge.configuration.GameConfiguration;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.common.io.Resources;

public class GameFactory {

	public static Game Build(GameConfiguration configuration) {
		return new Game(configuration);
	}
	
	public static Game Build() throws FileNotFoundException, IOException, ParseException {
		
		JSONParser parser = new JSONParser();
		
		URL url = Resources.getResource("game-configuration.json");
		String configurationJson = Resources.toString(url, StandardCharsets.UTF_8);
		
		JSONObject json =(JSONObject)parser.parse(configurationJson);
		
		GameConfiguration configuration = new GameConfiguration();
		
		return Build(configuration);
	}
	
}
