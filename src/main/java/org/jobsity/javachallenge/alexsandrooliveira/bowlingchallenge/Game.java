package org.jobsity.javachallenge.alexsandrooliveira.bowlingchallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.jobsity.javachallenge.alexsandrooliveira.bowlingchallenge.configuration.GameConfiguration;

public class Game {

	private GameConfiguration _configuration;
	
	//TODO - Colocar no injetor de dep
	public Game(GameConfiguration configuration) {
		_configuration = configuration;
	}

	public void Run() throws IOException {
		
    	BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)); 
		String name = reader.readLine();
        System.out.println( "Hello World! " + name + _configuration.toString() );      
	
	}

}
