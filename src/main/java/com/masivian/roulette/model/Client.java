package com.masivian.roulette.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Data;

@Data
@RedisHash("Client")
public class Client {
	
	@Id
	private Long id;
	private String username;
	private long credit; 
	
}
