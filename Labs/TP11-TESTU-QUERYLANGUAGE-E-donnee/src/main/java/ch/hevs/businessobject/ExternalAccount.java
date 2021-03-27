package ch.hevs.businessobject;

import javax.persistence.Entity;

@Entity
public class ExternalAccount extends Account {

	private String socialNumber;

	public String getSocialNumber() {
		return socialNumber;
	}
	public void setSocialNumber(String n) {
		this.socialNumber = n;
	}

	// Constructors
	public ExternalAccount() {
		super();
	}
	public ExternalAccount(String n, String description) {
		super(description);
		this.socialNumber = n;
	}
}
