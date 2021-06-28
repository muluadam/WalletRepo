package com.digital.wallet.models;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Card {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private long id;
	private long cardNumber;
	private int cardCSV;
	private LocalDate expiryDate;
	
	
	public Card(long cardNumber, int cardCSV, LocalDate expiryDate) {
		super();
		this.cardNumber = cardNumber;
		this.cardCSV = cardCSV;
		this.expiryDate = expiryDate;
	}
	
	public Card() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LocalDate getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getCardCSV() {
		return cardCSV;
	}
	public void setCardCSV(int cardCSV) {
		this.cardCSV = cardCSV;
	}
	

}
