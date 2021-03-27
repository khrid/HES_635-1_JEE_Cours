package ch.hevs.businessobject;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String lastname;
	private String firstname;

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}
	public void setLastname(String nom) {
		this.lastname = nom;
	}

	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	// constructors
	public Person() {
	}
	public Person(String lastname, String firstname) {
		this.lastname = lastname;
		this.firstname = firstname;
	}
}
