package entity;

public class Student {
	private Integer ID;
	private String firstName;
	private String lastName;
	private String email;
	
	public Student(Integer iD, String firstName, String lastName, String email) {
		super();
		ID = iD;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Student(String firstName, String lastName, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getID() {
		return ID;
	}

	public void setID(Integer iD) {
		ID = iD;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "Student [ID=" + ID + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email + "]";
	}
	
	
}
