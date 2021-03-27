package ch.hevs.businessobject;

import java.util.HashSet;
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

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "FK_INTERNALACCOUNT")
	private Set<Operation> operations;
	@ManyToMany
	private Set<Client> owners;

	public float getSaldo() {
		return saldo;
	}
	public void setSaldo(float saldo) {
		this.saldo = saldo;
	}
	
	public String getNumber() {
		return number;
	}
	public void setNumber(String numero) {
		this.number = numero;
	}

	public Set<Operation> getOperations() {
		return operations;
	}
	public void setOperations(Set<Operation> operations) {
		this.operations = operations;
	}

	public Set<Client> getOwners() {
		return owners;
	}
	public void setOwners(Set<Client> owners) {
		this.owners = owners;
	}

	// constructors
	public InternalAccount() {
		super();
	}
	public InternalAccount(String number, String description, float saldo) {
		super(description);
		this.number = number;
		this.saldo = saldo;
		operations = new HashSet<Operation>();
		owners = new HashSet<Client>();

	}

	// helper methods
	public void addOwner(Client client) {
		owners.add(client);
	}
	public void addOperation(Operation o) {
		operations.add(o);
	}
}
