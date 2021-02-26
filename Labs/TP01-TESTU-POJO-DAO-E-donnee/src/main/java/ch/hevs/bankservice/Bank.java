package ch.hevs.bankservice;

import java.util.List;

import ch.hevs.businessobject.Client;
import ch.hevs.businessobject.Account;
import ch.hevs.dao.ClientDAO;
import ch.hevs.dao.AccountDAO;
import ch.hevs.exception.BankException;


public class Bank {

	public List<Client> getClients() throws BankException {
		List<Client> result;
		result = ClientDAO.getClients();
		return result;
	}

	public void storeNewClient(Client c) throws BankException {
		ClientDAO.save(c);
	}

	public void deleteClient(Client c) throws BankException {
		ClientDAO.delete(c);
	}

	public void modifyClient(Client c) throws BankException {
		ClientDAO.modify(c);
	}

	public List<Account> getClientAccount(String name) throws BankException {
		return AccountDAO.getClientAccount(name);
	}

	public void transfer(Account source, Account destination, int amount)
			throws BankException {
		source.debit(amount);
		destination.credit(amount);
		AccountDAO.saveOrUpdate(source);
		AccountDAO.saveOrUpdate(destination);

	}
}
