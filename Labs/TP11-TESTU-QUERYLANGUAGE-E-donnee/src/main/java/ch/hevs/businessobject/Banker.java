package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Banker extends Person {

	private String email;

	@ManyToOne
	private Agency agency;

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public Agency getAgency() {
		return agency;
	}
	public void setAgency(Agency agency) {
		this.agency = agency;
	}

	// constructors
	public Banker() {
		super();
	}
	public Banker(String lastname, String firstname, String email) {
		super(lastname, firstname);
		this.email = email;
	}
}
