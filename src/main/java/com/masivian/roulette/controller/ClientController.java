package com.masivian.roulette.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.masivian.roulette.model.Client;
import com.masivian.roulette.service.ClientService;

@RestController
public class ClientController {
	
	@Autowired
	ClientService clientService;
	
	@PostMapping(value = "/clients", consumes = "application/json", produces = "application/json")
	public @ResponseBody Long createClient(@RequestBody Client client ) {
			
		if(client != null) {
			return clientService.createClient(client);
		}
		
		return (long) -1;
	}
	
	@GetMapping(value = "/clients/{idClient}", produces = "application/json")
	public @ResponseBody Client findClient(@PathVariable("idClient") long idClient ) {
		
		return clientService.findClient(idClient);
	}
	
	@GetMapping(value = "/clients", produces = "application/json")
	public @ResponseBody List<Client> findAllClients() {
		
		return clientService.findAllClients();
	}
	
	@DeleteMapping(value = "/clients/{idClient}")
	public @ResponseBody boolean deleteRoulette(@PathVariable("idClient")long idClient){
		return clientService.deleteClient(idClient);
	}
	
}
