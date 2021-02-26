package ch.hevs.businessobject;

public class Client {
	private long id;

	private String lastname;

	private String firstname;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	@Override
	public String toString() {
		String result = id + "-" + lastname + "-" + firstname;
		return result;

	}

}
