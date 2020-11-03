package com.masivian.roulette.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masivian.roulette.model.Bet;
import com.masivian.roulette.model.Roulette;
import com.masivian.roulette.repositories.RouletteRepository;
import com.masivian.roulette.service.ClientService;
import com.masivian.roulette.service.RouletteService;

@Service
public class RouletteServiceImpl implements RouletteService {

	@Autowired
	private RouletteRepository rouletteRepository;

	@Autowired
	private ClientService clientService;

	public RouletteServiceImpl(RouletteRepository rouletteRepository) {
		this.rouletteRepository = rouletteRepository;
	}

	public Long createRouelette() {
		Roulette newRoulette = new Roulette();
		newRoulette.setOpen(false);
		newRoulette.setCountBets(0);
		Roulette savedRoulette = rouletteRepository.save(newRoulette);

		return savedRoulette.getId();
	}

	@Override
	public boolean openRoulette(Long id) {
		Optional<Roulette> rouletteTry = rouletteRepository.findById(id);
		if (rouletteTry.isPresent()) {
			Roulette roulette = rouletteTry.get();
			if(!roulette.isOpen()) {
				roulette.setOpen(true);
				Roulette savedRoulette = rouletteRepository.save(roulette);
				if (savedRoulette.isOpen() && roulette.getId() == savedRoulette.getId()) {

					return true;
				}
			}
			
		}

		return false;
	}

	public boolean makeBet(Bet bet, Long idRoulette) {
		Optional<Roulette> rouletteFind = this.rouletteRepository.findById(idRoulette);
		if (rouletteFind.isPresent()) {
			Roulette roulette = rouletteFind.get();
			if(roulette.isOpen()) {
				roulette.setCountBets(roulette.getCountBets() + 1);
				bet.setId((long) roulette.getCountBets());
				if (roulette.getBets() == null) {
					roulette.setBets(new ArrayList<Bet>());
				}
				roulette.getBets().add(bet);
				Roulette savedRoulette = rouletteRepository.save(roulette);
				if (savedRoulette.isOpen() && (roulette.getId() == savedRoulette.getId())) {

					return true;
				}
			}
		}

		return false;
	}

	public List<Bet> closeRoulette(long idRoulette) {
		Random random = new Random();
		int winnerNumber = random.nextInt(36);
		String winnerColor;
		if (winnerNumber % 2 == 0) {
			winnerColor = "red";
		} else {
			winnerColor = "black";
		}

		return updateWinners(idRoulette, winnerNumber, winnerColor);
	}

	public List<Bet> updateWinners(long idRoulette, int winnerNumber, String winnerColor) {
		Optional<Roulette> rouletteFind = this.rouletteRepository.findById(idRoulette);
		if (rouletteFind.isPresent()) {
			Roulette roulette = rouletteFind.get();
			if (roulette.isOpen()) {
				roulette.setWinnerNumber(winnerNumber);
				roulette.setOpen(false);
				for (Bet bet : roulette.getBets()) {
					if (bet.getNumber() == winnerNumber) {
						bet.setWinner(true);
						bet.setAmountWon((bet.getQuantity() * 5));
					} else if (bet.getColor().equalsIgnoreCase(winnerColor)) {
						bet.setWinner(true);
						bet.setAmountWon((bet.getQuantity() * (long) 1.8));
					} else {
						bet.setWinner(false);
						bet.setAmountWon(0);
					}
					clientService.updateClientWinner(bet.getClientId(), bet.getAmountWon(), bet.getQuantity());
				}

				return this.rouletteRepository.save(roulette).getBets();
			}

		}
		
		return null;
	}

	public Roulette findRoulette(long idRoulette) {
		Optional<Roulette> rouletteFound = rouletteRepository.findById(idRoulette);
		if (rouletteFound.isPresent()) {
			
			return rouletteFound.get();
		}
		
		return null;
	}

	public List<Roulette> findAllRoulettes() {
		List<Roulette> rouletteFound = (List<Roulette>) rouletteRepository.findAll();
		if (!rouletteFound.isEmpty()) {
			
			return rouletteFound;
		}
		
		return null;
	}
	
	public boolean deleteRoulette(long idRoulette) {
		Optional<Roulette> rouletteFound = rouletteRepository.findById(idRoulette);
		if(rouletteFound.isPresent()) {
			rouletteRepository.deleteById(idRoulette);
			
			return true;
		}
		
		return false;
		
	}
}
