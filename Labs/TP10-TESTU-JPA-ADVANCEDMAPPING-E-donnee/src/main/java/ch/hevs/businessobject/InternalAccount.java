package ch.hevs.businessobject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class InternalAccount extends Account {
	
	private String number;
	private float saldo;

	// relations
	@ManyToMany(mappedBy = "internalAccounts", cascade = CascadeType.ALL)
	private List<Client> owners;
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_internalaccount", nullable = true)
	private List<Operation> operations;

	// number
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	
	// saldo
	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}

	// constructors
	public InternalAccount() {
		this.operations = new ArrayList<>();
	}
	public InternalAccount(String number, String description, float saldo) {
		super(description);
		this.number = number;
		this.saldo = saldo;
		this.operations = new ArrayList<>();
		this.owners = new ArrayList<>();
	}

	// helper methods
	public void addOwner(Client client) {
		owners.add(client);
	}
	public void addOperation(Operation o) {
		operations.add(o);
	}
}
