package org.alexoliveira.bowlingchallenge.domain.interfaces.infra;

import java.util.List;

import org.alexoliveira.bowlingchallenge.domain.models.PlayerThrow;

public interface GameFileReader {
	List<PlayerThrow> readFile(String fileName) throws Exception;
}
