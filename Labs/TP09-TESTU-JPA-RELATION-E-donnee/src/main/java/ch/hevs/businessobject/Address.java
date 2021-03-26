package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String postalCode;
	private String street;
	private String city;

	
	// id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	// postalCode
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
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
