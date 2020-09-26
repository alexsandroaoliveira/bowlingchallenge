package org.alexoliveira.bowlingchallenge.application;

public class App 
{
    public static void main( String[] args )
    {
		try {
			if (args.length == 0) {
				throw new Exception("Invalid game file");
			}
		
			GameApp gameApp = new GameApp();
			gameApp.Run(args[0]);
	        
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} 
     
    }
}
