package model;


public class Person {
	
	private String firstName;
	private String lastName;
	private String eMail;
	private String phoneNumber;
	
	public Person(String  firstName, String  lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = "some@email.com";
		this.phoneNumber = "+324111111";
	}
	
	public String  getFirstName() {
		return firstName;
	}
	public void setFirstName(String  firstName) {
		this.firstName = firstName;
	}
	public String  getLastName() {
		return lastName;
	}
	public void setLastName(String  lastName) {
		this.lastName = lastName;
	}
	public String  geteMail() {
		return eMail;
	}
	public void seteMail(String  eMail) {
		this.eMail = eMail;
	}
	public String  getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String  phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return firstName + " " + lastName;
	}
	

}
