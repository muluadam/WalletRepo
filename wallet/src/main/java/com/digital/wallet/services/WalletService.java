package com.digital.wallet.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.digital.wallet.models.Transaction;

public interface WalletService {
	
	ResponseEntity<String> addAmount(long from, long to, float amount,String userEmail);
	List<Transaction> findWalletTransactions(long walletId, String email);
}
