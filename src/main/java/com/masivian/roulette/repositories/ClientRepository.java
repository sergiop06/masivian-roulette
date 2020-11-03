package com.masivian.roulette.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.masivian.roulette.model.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client, Long>{
	Optional<Client> findById(Long id);

}
