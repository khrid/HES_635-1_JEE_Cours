package ch.hevs.test;

import java.util.List;

import ch.hevs.bankservice.Bank;
import ch.hevs.businessobject.Account;
import ch.hevs.exception.BankException;


public class TestTransfer {
	public static void main(String args[]) {
		try {
			Bank bank = new Bank();

			List<Account> accounts1 = bank.getClientAccount("Zidane");
			Account originAccount = accounts1.get(0);

			List<Account> accounts2 = bank.getClientAccount("Platini");
			Account targetAccount = accounts2.get(0);

			bank.transfer(originAccount, targetAccount, 1000);
		} catch (BankException e) {
			e.printStackTrace();
		}
	}
}
