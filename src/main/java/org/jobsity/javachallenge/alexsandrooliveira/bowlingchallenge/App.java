package org.jobsity.javachallenge.alexsandrooliveira.bowlingchallenge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class App 
{
    public static void main( String[] args )
    {
		try {
		
			Game game = GameFactory.Build();
			game.Run();
	        
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
     
    }
}
