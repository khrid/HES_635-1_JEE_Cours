package ch.hevs.businessobject;

import javax.persistence.Embeddable;

@Embeddable
public class Address {
	
	private String postalCode;
	private String street;
	private String city;

	// postalCode
	public String getCodePostal() {
		return postalCode;
	}
	public void setCodePostal(String codePostal) {
		this.postalCode = codePostal;
	}

	// street
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}

	// city
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}

	// constructors
	public Address() {
	}
	public Address(String postalCode, String street, String city) {
		this.postalCode = postalCode;
		this.street = street;
		this.city = city;
	}
}
