package com.digital.wallet.models;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wallets")
public class Wallet {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long walletId;
	private double amount;
	@ManyToOne
	@JoinColumn(name = "customer_id")
	private Customer walletHolder;

	public Wallet(long walletId, double amount) {
		super();
		this.walletId = walletId;
		this.amount = amount;
		
	}

	public Wallet() {
		super();
		// TODO Auto-generated constructor stub
	}

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "wallet_transactions", joinColumns = {
			@JoinColumn(name = "walletId") }, inverseJoinColumns = { @JoinColumn(name = "transactionId") })
	private List<Transaction> transactions;

	public List<Transaction> getTransactions() {
		return transactions;
	}

	public void setTransactions(List<Transaction> transactions) {
		this.transactions = transactions;
	}

	public long getWalletId() {
		return walletId;
	}

	public void setWalletId(long walletId) {
		this.walletId = walletId;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Customer getWalletHolder() {
		return walletHolder;
	}

	public void setWalletHolder(Customer walletHolder) {
		this.walletHolder = walletHolder;
	}

}
