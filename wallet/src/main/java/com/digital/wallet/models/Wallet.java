package com.digital.wallet.models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;



@Entity
@Table(name = "wallets")
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private float amount;
	private boolean isActive;

	private String walletTag;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer walletHolder;
	

	
	public Wallet(float amount) {
		super();
		this.amount = amount;
		this.isActive=true;
		//generate UUID
		setWalletTag(GetRandom.generate(6));
		
	}

	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	

	public long getWalletId() {
		return id;
	}

	public void setWalletId(long walletId) {
		this.id = walletId;
	}

	public float getAmount() {
		return amount;
	}

	public void setAmount(float amount) {
		this.amount = amount;
	}

	public Customer getWalletHolder() {
		return walletHolder;
	}

	public void setWalletHolder(Customer walletHolder) {
		this.walletHolder = walletHolder;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isActive() {
		return isActive;
	}

	public void setActive(boolean isActive) {
		this.isActive = isActive;
	}

	public String getWalletTag() {
		return walletTag;
	}
	public void setWalletTag(String walletTag) {
		this.walletTag = walletTag;
	}

}
