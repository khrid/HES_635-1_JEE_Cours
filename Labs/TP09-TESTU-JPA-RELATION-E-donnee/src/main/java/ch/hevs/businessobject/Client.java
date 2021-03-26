package ch.hevs.businessobject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String description;
	private String lastname;
	private String firstname;

	// add relations
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_agency", nullable = true)
	private Agency agency;
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_address", nullable = true)
	private Address address;
	@OneToMany(cascade = CascadeType.ALL)
	private List<ExternalAccount> externalAccounts;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<InternalAccount> internalAccounts;
	
	// id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	// description
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	// lastname
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	// firstname
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	// agency
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	// address
	public Address getAddress() { return address; }
	public void setAddress(Address address) { this.address = address; }

	// externalAccount
	public List<ExternalAccount> getExternalAccounts() { return externalAccounts; }
	public void setExternalAccounts(List<ExternalAccount> externalAccounts) { this.externalAccounts = externalAccounts; }

	// internalAccounts
	public List<InternalAccount> getInternalAccounts() { return internalAccounts; }
	public void setInternalAccounts(List<InternalAccount> internalAccount) { this.internalAccounts = internalAccount; }

	// constructors
	public Client() {
	}
	public Client(String lastname, String firstname, String description) {
		this.description = description;
		this.lastname = lastname;
		this.firstname = firstname;
		this.internalAccounts = new ArrayList<>();
		this.externalAccounts = new ArrayList<>();
	}

	// helper methods
	public void addExternalAccount(ExternalAccount externalAccount) {
		this.externalAccounts.add(externalAccount);
	}
	public void addInternalAccount(InternalAccount internalAccount) {
		this.internalAccounts.add(internalAccount);
		internalAccount.addClient(this);
	}
}
