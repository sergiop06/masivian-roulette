package com.masivian.roulette.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.roulette.model.Bet;
import com.masivian.roulette.model.BetBody;
import com.masivian.roulette.model.Roulette;
import com.masivian.roulette.service.RouletteService;

@RestController
public class RouletteController {
	
	@Autowired
	RouletteService rouletteService;
	
	@PostMapping(value = "/roulettes", produces = "application/json")
	public @ResponseBody Long createRoulette() {
		
		return rouletteService.createRouelette();
	}
	
	@PostMapping(value = "/roulettes/{idRoulette}/open", produces = "application/json")
	public @ResponseBody boolean openRoulette(@PathVariable("idRoulette") long idRoulette) {
		return rouletteService.openRoulette(idRoulette);	
	}
	
	@PostMapping(value = "/roulettes/{idRoulette}/makebet", consumes = "application/json", produces = "application/json")
	public @ResponseBody boolean makeBet(
			@RequestHeader(name="CLIENT-ID",required = true) long clientId,
			@RequestBody BetBody bet,
			@PathVariable("idRoulette") long idRoulette) {
		int checkTopBet = bet.getQuantity().compareTo(new BigDecimal(10000));
		if(checkTopBet==-1 || checkTopBet==0 ) {
			Bet newBet= new Bet();
			newBet.setClientId(clientId);
			newBet.setQuantity(bet.getQuantity());
			if(bet.isBetNumber()) {
				if(bet.getNumber() >0 && bet.getNumber()<37) {
					newBet.setNumber(bet.getNumber());
					if(bet.getNumber()%2 == 0) {
						newBet.setColor("red");
					}else {
						newBet.setColor("black");
					}
				}else return false;
			}else {
				newBet.setNumber(-1);
				if(bet.getColor()!=null && bet.getColor()!="") {
					newBet.setColor(bet.getColor());
				}else return false;
			}
			
			return rouletteService.makeBet(newBet, idRoulette);
			
		}else return false;
	}
	
	@PostMapping(value = "/roulettes/{idRoulette}/close", produces = "application/json")
	public @ResponseBody List<Bet> closeRoulette(@PathVariable("idRoulette")long idRoulette){
		return rouletteService.closeRoulette(idRoulette);
	}
	
	@GetMapping(value = "/roulettes/{idRoulette}", produces = "application/json")
	public @ResponseBody Roulette findRoulette(@PathVariable("idRoulette")long idRoulette){
		return rouletteService.findRoulette(idRoulette);
	}
	
	@GetMapping(value = "/roulettes", produces = "application/json")
	public @ResponseBody List<Roulette> findAllRoulettes(){
		return rouletteService.findAllRoulettes();
	}
	
	@DeleteMapping(value = "/roulettes/{idRoulette}")
	public @ResponseBody boolean deleteRoulette(@PathVariable("idRoulette")long idRoulette){
		return rouletteService.deleteRoulette(idRoulette);
	}
	
}