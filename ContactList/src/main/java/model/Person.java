package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "person")

public class Person {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int idperson;
	@Column(name = "firstname")
	private String firstName;
	@Column(name = "lastname")
	private String lastName;
	@Column(name = "email")
	private String eMail;
	@Column(name = "phone")
	private String phoneNumber;
	
	public Person(String  firstName, String  lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.eMail = "some@email.com";
		this.phoneNumber = "+324111111";
	}
	public Person() { this(null, null); }
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
