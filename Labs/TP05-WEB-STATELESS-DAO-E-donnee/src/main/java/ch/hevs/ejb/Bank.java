package ch.hevs.ejb;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Client;
import ch.hevs.exception.BankException;

import javax.ejb.Local;
import java.util.List;

@Local
public interface Bank {
    List<Client> getClients();

    Client getClientByName(String nom);

    void storeNewClient(Client c) throws BankException;

    void deleteClient(Client c) throws BankException;

    void modifyClient(Client c) throws BankException;

    void transfer(Account source, Account destination, int amount);
}
