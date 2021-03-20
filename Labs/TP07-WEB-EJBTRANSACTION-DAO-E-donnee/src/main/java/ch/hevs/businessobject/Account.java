package ch.hevs.businessobject;

import java.io.Serializable;

import ch.hevs.exception.BankException;

public class Account implements Serializable {

	private static final long serialVersionUID = 6469518891904579456L;

	private Long id;

	private String number;

	private long saldo;

	private Client owner;

	private String description;

	public static long MIN_SOLDE = 0;

	public static long MAX_SOLDE = 1000000;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public void setOwner(Client owner) {
		this.owner = owner;
	}

	public long getSaldo() {
		return saldo;
	}

	public void setSaldo(long saldo) {
		this.saldo = saldo;
	}

	public void debit(int amount) throws BankException {
		long newAmount = getSaldo() - amount;
		if (newAmount < 0) {
			throw new BankException("Not enough money !");
		} else {
			setSaldo(newAmount);
		}
	}

	public void credit(int amount) {
		setSaldo(getSaldo() + amount);

	}

}
