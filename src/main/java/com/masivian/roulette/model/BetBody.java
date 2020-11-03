package com.masivian.roulette.model;


import java.math.BigDecimal;

import lombok.Data;

@Data
public class BetBody {
	private int number;
	private String color;
	private BigDecimal quantity;
	private boolean betNumber;
	
}
