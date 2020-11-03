package com.masivian.roulette.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masivian.roulette.model.Client;
import com.masivian.roulette.repositories.ClientRepository;
import com.masivian.roulette.service.ClientService;

@Service
public class ClientServiceImpl implements ClientService{
	@Autowired
	private ClientRepository clientRepository; 
	
	@Override
	public Long createClient(Client client) {
		
		if(client != null) {
			System.out.println("Clientservice impl about to save line19");
			Client savedClient = clientRepository.save(client);
			System.out.println("after client repository save");
			return savedClient.getId();
		}else {
			System.out.println("client service create client is null");
			return (long)-1;
		}
	}
	
	public void updateClientWinner(long idClient,long amountWon,long amountBet) {
		Optional<Client>clientFound = clientRepository.findById(idClient);
		if(clientFound.isPresent()) {
			Client client = clientFound.get();
			if(amountWon == 0) {
				client.setCredit(client.getCredit()-amountBet);
			}else {
				client.setCredit(client.getCredit()+amountWon);
			}
			clientRepository.save(client);	
		}
	}

	public Client findClient(long idClient) {
		Optional<Client>clientFound = clientRepository.findById(idClient);
		if(clientFound.isPresent()) {
			return clientFound.get();
		}
		return null;
	}

	public List<Client> findAllClients() {
		List<Client> clients = (List<Client>) clientRepository.findAll();
		if(!clients.isEmpty()) {
			return clients;
		}
		return null;
	}
	
	public boolean deleteClient(long idClient) {
		Optional<Client> clientFound = clientRepository.findById(idClient);
		if(clientFound.isPresent()) {
			clientRepository.deleteById(idClient);
			return true;
		}
		return false;
	}
}
