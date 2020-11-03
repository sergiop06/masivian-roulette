package com.masivian.roulette.model;


import java.math.BigDecimal;

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
	private BigDecimal quantity;
	private boolean winner;
	private BigDecimal amountWon;
	
}
