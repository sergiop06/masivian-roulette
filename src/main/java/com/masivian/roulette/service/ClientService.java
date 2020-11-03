package com.masivian.roulette.service;


import java.util.List;

import com.masivian.roulette.model.Client;

public interface ClientService {

	public Long createClient(Client client);
	public void updateClientWinner(long idClient,long amountWon,long amountBet);
	public Client findClient(long idClient);
	public boolean deleteClient(long idClient);
	public List<Client> findAllClients();

}
