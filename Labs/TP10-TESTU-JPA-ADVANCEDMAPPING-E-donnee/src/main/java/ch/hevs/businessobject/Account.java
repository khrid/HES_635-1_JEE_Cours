package ch.hevs.businessobject;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Account {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	private String description;

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

	// constructors
	protected Account() {
	}
	public Account(String description) {
		this.description = description;
	}
}
