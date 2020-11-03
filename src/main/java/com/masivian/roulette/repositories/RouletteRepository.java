package com.masivian.roulette.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.masivian.roulette.model.Roulette;

public interface RouletteRepository extends CrudRepository<Roulette, Long>{
	Optional<Roulette> findById(Long id);
}
