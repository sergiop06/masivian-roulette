package com.masivian.roulette.service;

import java.util.List;

import com.masivian.roulette.model.Bet;
import com.masivian.roulette.model.Roulette;

public interface RouletteService {

	Long createRouelette();
	boolean openRoulette(Long idRoulette);
	boolean makeBet(Bet bet, Long idRoulette);
	List<Bet> closeRoulette(long idRoulette);
	Roulette findRoulette(long idRoulette);
	List<Roulette> findAllRoulettes();
}
