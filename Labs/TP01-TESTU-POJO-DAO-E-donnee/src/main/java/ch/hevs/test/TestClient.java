package ch.hevs.test;

import java.util.List;

import ch.hevs.bankservice.Bank;
import ch.hevs.businessobject.Client;
import ch.hevs.exception.BankException;


public class TestClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Bank bank = new Bank();
			List<Client> clients = bank.getClients();
			for (Client c : clients) {
				System.out.println(c);
			}

			Client c = new Client();
			c.setLastname("testlastname");
			c.setFirstname("testfirstname");

			bank.storeNewClient(c);
			c.setLastname("newlastname");
			bank.modifyClient(c);

			//bank.deleteClient(c);

		} catch (BankException e) {
			e.printStackTrace();
		}

	}

}
