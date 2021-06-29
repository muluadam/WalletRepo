package com.digital.wallet.services;


import org.springframework.http.ResponseEntity;

import com.digital.wallet.modelRequests.CardInfo;


public interface WalletService {
	
	ResponseEntity<String> transferAmount(long from, long to, float amount,String userEmail);
	ResponseEntity<?> findWalletTransactions(long walletId, String email);
	ResponseEntity<String> topUpMoney(long walletId, CardInfo cardInfo, float amount, String email);
}
