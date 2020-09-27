package org.alexoliveira.bowlingchallenge.application;

import org.alexoliveira.bowlingchallenge.application.interfaces.GameApp;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
		if (args.length == 0) {
			System.out.println("Invalid game file");;
		}

    	try (AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class)){
	    	GameApp gameApp = context.getBean(GameApp.class);
			gameApp.Run(args[0]);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    }
}
