package com.masivian.roulette.model;


import lombok.Data;

@Data
public class BetBody {
	private int number;
	private String color;
	private long quantity;
	private boolean betNumber;
	
}
