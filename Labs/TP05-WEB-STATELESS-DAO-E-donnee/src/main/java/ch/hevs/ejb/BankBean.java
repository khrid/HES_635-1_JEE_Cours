package ch.hevs.ejb;

import ch.hevs.businessobject.Account;
import ch.hevs.businessobject.Client;
import ch.hevs.dao.AccountDAO;
import ch.hevs.dao.ClientDAO;
import ch.hevs.exception.BankException;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import java.util.List;

@Stateless
@TransactionAttribute(value= TransactionAttributeType.REQUIRED)
public class BankBean implements Bank{

    public List<Client> getClients() throws BankException {
        List<Client> result;
        result = ClientDAO.getClients();
        return result;
    }

    public Client getClientByName(String nom) {

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
        source.debit(amount);
        destination.credit(amount);
        AccountDAO.saveOrUpdate(source);
        AccountDAO.saveOrUpdate2(destination);
    }
}
