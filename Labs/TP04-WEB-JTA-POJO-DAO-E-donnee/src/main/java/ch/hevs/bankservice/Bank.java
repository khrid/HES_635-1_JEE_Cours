package ch.hevs.bankservice;

import java.util.List;

import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;

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

	public Client getClientByName(String nom) {
		// what do you think about this implementation ?
		Client result = null;
		try {
			List<Client> clients = getClients();
			for (Client c : clients) {
				if (c.getLastname().equals(nom)) {
					result = c;
					break;
				}
			}
		} catch (BankException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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

	public void transfer(Account source, Account destination, int amount)
			throws BankException {
		Context ctx = null;
		UserTransaction tx = null;
		try {

			// Initialisation of the transactional context
			ctx = new InitialContext();
			tx = (UserTransaction) ctx.lookup("java:comp/UserTransaction"); // using the UserTransaction object of the Java Transaction API (JTA)
																			// it allows using transactions outsite EJBs

			// Start transaction
			tx.begin();
			source.debit(amount);
			destination.credit(amount);
			AccountDAO.saveOrUpdate(source);
			AccountDAO.saveOrUpdate2(destination);
			tx.commit();

			System.out.println("The transaction has just been validated (no random exception).");
		} catch (Exception e) {
			try {
				tx.rollback();
				throw new BankException("Failure of transfer");
			} catch (IllegalStateException e1) {
				e1.printStackTrace();
			} catch (SecurityException e1) {
				e1.printStackTrace();
			} catch (SystemException e1) {
				e1.printStackTrace();
			}
		}
	}
}
