package ch.hevs.businessobject;

import ch.hevs.exception.BankException;

public class Account {
	private Long id;

	private String number;

	private long saldo;

	private Client owner;

	public static long MIN_SOLDE = 0;

	public static long MAX_SOLDE = 1000000;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Client getOwner() {
		return owner;
	}

	public void setProprietaire(Client owner) {
		this.owner = owner;
	}

	public long getSaldo() {
		return saldo;
	}

	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}

	public void debit(int amount) throws BankException {
		long nouveauMontant = getSaldo() - amount;
		if (nouveauMontant < 0) {
			throw new BankException("Not enough money !");
		} else {
			setSaldo(nouveauMontant);
		}
	}

	public void credit(int amount) {
		setSaldo(getSaldo() + amount);

	}

}
