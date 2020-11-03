package com.masivian.roulette.service;


import java.math.BigDecimal;
import java.util.List;

import com.masivian.roulette.model.Client;

public interface ClientService {

	public Long createClient(Client client);
	public void updateClientWinner(long idClient,BigDecimal amountWon,BigDecimal amountBet);
	public Client findClient(long idClient);
	public boolean deleteClient(long idClient);
	public List<Client> findAllClients();

}
