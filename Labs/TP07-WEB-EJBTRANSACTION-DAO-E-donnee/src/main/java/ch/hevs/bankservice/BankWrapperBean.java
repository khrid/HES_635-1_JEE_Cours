package ch.hevs.bankservice;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Client;
import ch.hevs.exception.BankException;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.List;

@Stateless
public class BankWrapperBean implements BankWrapper{

    @EJB
    Bank bank;

    @Override
    @TransactionAttribute(value = TransactionAttributeType.REQUIRED)
    public void transferAndCreateClient(Account source, Account destination, int amount) throws BankException {
        this.bank.createClient();
        this.bank.transfer(source, destination, amount);
    }

    @Override
    public Client getClientByName(String name) {
        return bank.getClientByName(name);
    }

    @Override
    public List<Client> getClients() {
        return bank.getClients();
    }


}
