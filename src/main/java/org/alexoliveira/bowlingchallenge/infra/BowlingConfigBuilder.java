package org.alexoliveira.bowlingchallenge.infra;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.alexoliveira.bowlingchallenge.domain.models.configuration.GameConfiguration;

import com.google.gson.Gson;

public class BowlingConfigBuilder implements GameConfigBuilder {

	@Override
	public GameConfiguration build() {
		Gson g = new Gson();
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("game-configuration.json");
		
	    StringBuilder jsonString = new StringBuilder();
	    
	    try (Reader reader = new BufferedReader(new InputStreamReader
	      (is, Charset.forName(StandardCharsets.UTF_8.name())))) {
	        int c = 0;
	        while ((c = reader.read()) != -1) {
	        	jsonString.append((char) c);
	        }
	    } catch (Exception e) {
	    	System.out.println("Error- Game Configuration read fail");
		}
	    
		GameConfiguration gameConfig = g.fromJson(jsonString.toString(), GameConfiguration.class);
		return gameConfig;
	}

}
