package ch.hevs.businessobject;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String postalCode;
	private String street;
	private String city;

	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalcode(String postalCode) {
		this.postalCode = postalCode;
	}

	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	// constructors
	public Address() {
	}
	public Address(String postalcode, String street, String city) {
		this.postalCode = postalcode;
		this.street = street;
		this.city = city;
	}
}
