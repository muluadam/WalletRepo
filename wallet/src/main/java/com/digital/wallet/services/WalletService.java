package com.digital.wallet.services;


import org.springframework.http.ResponseEntity;

import com.digital.wallet.modelRequests.CardInfo;
import com.digital.wallet.models.Wallet;


public interface WalletService {
	
	ResponseEntity<String> transferAmount(long from, String to, float amount,String userEmail);
	ResponseEntity<?> findWalletTransactions(long walletId, String email);
	//ResponseEntity<String> topUpMoney(long walletId, CardInfo cardInfo, float amount, String email);
	ResponseEntity<String> topUpMoney(long walletId, int pin, float amount, String email);
	

}
