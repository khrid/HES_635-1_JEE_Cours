package ch.hevs.businessobject;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Person {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String lastname;
	private String firstname;

	// id
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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

	// constructors
	public Person() {
	}
	public Person(String lastname, String firstname) {
		this.lastname = lastname;
		this.firstname = firstname;
	}
}
