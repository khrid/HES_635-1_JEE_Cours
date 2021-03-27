package ch.hevs.businessobject;

import javax.persistence.Entity;

@Entity
public class ExternalAccount extends Account {

	private String socialNumber;

	// socialNumber
	public String getSocialNumber() {
		return socialNumber;
	}
	public void setSocialNumber(String socialNumber) {
		this.socialNumber = socialNumber;
	}

	// constructors
	public ExternalAccount() {
	}
	public ExternalAccount(String socialNumber, String description) {
		this.socialNumber = socialNumber;
	}
}
