package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Banker extends Person {

	private String email;

	// relations
	@ManyToOne
	private Agency agency;

	// email
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	// constructors
	public Banker() {
	}
	public Banker(String lastname, String firstname, String email) {
		this.email = email;
	}

	public void setAgency(Agency agency) {
		this.agency = agency;
	}
}
