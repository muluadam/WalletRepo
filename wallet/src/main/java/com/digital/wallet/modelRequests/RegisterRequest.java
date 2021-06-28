package com.digital.wallet.modelRequests;

public class RegisterRequest {
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int customerPin;
	
	public RegisterRequest(String firstName, String lastName, String email, String password, int customerPin) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.customerPin = customerPin;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getCustomerPin() {
		return customerPin;
	}
	public void setCustomerPin(int customerPin) {
		this.customerPin = customerPin;
	}
	
	

}
