package com.digital.wallet.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.wallet.modelRequests.CardInfo;
import com.digital.wallet.models.Transaction;
import com.digital.wallet.services.WalletService;

@RestController
public class WalletController {
	@Autowired
	private WalletService walletService;
	
	
	@PostMapping("/wallet/{walletId}/transfer")
	public ResponseEntity<String> transfer(@PathVariable("walletId") long from,
			@RequestParam("to") long to,
            @RequestParam("amount") float amount, Principal p){
		if(p==null) return userNotFound();
		if(from == to || amount == 0) return new ResponseEntity<>("invalide request",HttpStatus.BAD_REQUEST);
		return walletService.transferAmount(from, to, amount,p.getName());
	}
	
	

	@GetMapping("/wallet/{id}/transactions")
	public ResponseEntity<?> getTransactions(@PathVariable("id") long walletId, Principal p){
		if(p==null) return userNotFound();
		return walletService.findWalletTransactions(walletId, p.getName());
		
	}
	@PostMapping("/wallet/{id}/topUp")
	public ResponseEntity<String> topUp(@PathVariable("id") long walletId,@RequestParam("amount") float amount,@RequestBody CardInfo card, Principal p){
		if(p==null) return userNotFound();
		if( amount == 0) return new ResponseEntity<>("invalide amount",HttpStatus.BAD_REQUEST);
		return walletService.topUpMoney(walletId, card, amount,p.getName());
		
}
	private ResponseEntity<String> userNotFound() {
		return new ResponseEntity<>("user not found",HttpStatus.BAD_REQUEST);
	}
	
	

}
