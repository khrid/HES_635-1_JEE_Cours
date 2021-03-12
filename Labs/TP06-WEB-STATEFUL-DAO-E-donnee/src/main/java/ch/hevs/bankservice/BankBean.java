package ch.hevs.bankservice;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import ch.hevs.businessobject.Client;
import ch.hevs.businessobject.Account;
import ch.hevs.dao.ClientDAO;
import ch.hevs.dao.AccountDAO;
import ch.hevs.exception.BankException;


@Stateful
public class BankBean implements Bank {

	List<String> history;

	@PostConstruct
	public void init() {
		this.history = new ArrayList<>();
	}

	@PreDestroy
	public void clear() {
		this.history = null;
	}

	@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
	public void transfer(Account source, Account destination, int amount)
			throws BankException {
		source.debit(amount);

		AccountDAO.saveOrUpdate(source);

		destination.credit(amount);

		AccountDAO.saveOrUpdate(destination);

		history.add(source.getOwner() + " transfered " + amount + " to " + destination.getOwner());

	}

	@Override
	public List<String> getHistory() {
		return history;
	}

	public List<Client> getClients() throws BankException {
		List<Client> result;

		result = ClientDAO.getClients();

		return result;
	}

	public Client getClientByName(String name) throws BankException {

		Client result = null;
		List<Client> clients = getClients();
		for (Client c : clients) {
			if (c.getLastname().equals(name)) {
				result = c;
				break;
			}
		}
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

}
