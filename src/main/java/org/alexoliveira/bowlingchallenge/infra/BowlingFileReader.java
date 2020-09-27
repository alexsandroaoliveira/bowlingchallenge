package org.alexoliveira.bowlingchallenge.infra;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.alexoliveira.bowlingchallenge.domain.interfaces.infra.GameFileReader;
import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrow;

public class BowlingFileReader implements GameFileReader {

	public List<PlayerThrow> readFile(String fileName) throws Exception
	{
		List<PlayerThrow> result = new ArrayList<PlayerThrow>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    String line;
		    int lineNumber = 1;
		    while ((line = br.readLine()) != null) {
		    	result.add(readLine(line, lineNumber));
		    	lineNumber++;
		    }
		}
		
		return result;
	}

	private PlayerThrow readLine(String line, int lineNumber) throws Exception {
		String regexPattern = "([\\w\\s]*)\\t([\\dfF]*)";
		Pattern p = Pattern.compile(regexPattern);

		Matcher m = p.matcher(line);

		PlayerThrow gt = new PlayerThrow();
		
		if (m.find()) {
			gt.setPlayerName(m.group(1));
			gt.setPinfalls(m.group(2));
		}	else {
			throw new Exception("Invalid line ["+lineNumber+"] " + line);
		}		
		
		return gt;
	}
}
