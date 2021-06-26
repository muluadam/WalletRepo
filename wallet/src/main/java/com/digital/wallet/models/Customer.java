package com.digital.wallet.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "customers")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long customerId;
	private String firstName;
	private String lastName;
	private String email;
	private String password;
	private int customerPin;
	private long customerTag;

	public Customer(long customerId, String firstName, String lastName, String email, String password, long customerTag,
			List<Wallet> wallets) {
		super();
		this.customerId = customerId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.customerTag = customerTag;
		this.wallets = wallets;
		this.password=password;
	}

	@OneToMany(mappedBy = "walletHolder", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Wallet> wallets;

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}

	public List<Wallet> getWallets() {
		return wallets;
	}

	public void setWallets(List<Wallet> wallets) {
		this.wallets = wallets;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
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

	public long getCustomerTag() {
		return customerTag;
	}

	public void setCustomerTag(long customerTag) {
		this.customerTag = customerTag;
	}
}
