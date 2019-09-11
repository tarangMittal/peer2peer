package org.booksforyou.model;

public class User {

	private int userId;
	private String password;
	private String firstname;
	private String lastname;
	private String homeAddress;
	private String alternativeAddress;
	private String emailId;
	private String phoneNumber;
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getAlternativeAddress() {
		return alternativeAddress;
	}
	public void setAlternativeAddress(String alternativeAddress) {
		this.alternativeAddress = alternativeAddress;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", firstname=" + firstname + ", lastname=" + lastname
				+ ", homeAddress=" + homeAddress + ", alternativeAddress=" + alternativeAddress + ", emailId=" + emailId
				+ ", phoneNumber=" + phoneNumber + "]";
	}	

}


