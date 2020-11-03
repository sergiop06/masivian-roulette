package com.masivian.roulette.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("Roulette")
public class Roulette {
	@Id
	private Long id;
	private boolean isOpen;
	private int winnerNumber;
	private int countBets;
	private List<Bet> bets;
}
