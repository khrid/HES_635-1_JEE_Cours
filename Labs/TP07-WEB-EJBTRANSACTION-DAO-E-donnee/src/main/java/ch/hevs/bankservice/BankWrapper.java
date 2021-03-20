package ch.hevs.bankservice;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Client;
import ch.hevs.exception.BankException;

import java.util.List;

public interface BankWrapper {
    void transferAndCreateClient(Account source, Account destination, int amount) throws BankException;
    Client getClientByName(String name);
    List<Client> getClients();
}
