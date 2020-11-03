package com.masivian.roulette.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("Bet")
public class Bet {
	
	@Id
	private Long id;
	private long clientId;
	private int number;
	private String color;
	private long quantity;
	private boolean winner;
	private long amountWon;
	
}
