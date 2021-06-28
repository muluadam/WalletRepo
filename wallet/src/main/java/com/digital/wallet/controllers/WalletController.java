package com.digital.wallet.controllers;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.wallet.models.Transaction;
import com.digital.wallet.services.WalletService;

@RestController
public class WalletController {
	@Autowired
	private WalletService walletService;
	
	
	@PostMapping("/transfer")
	public ResponseEntity<String> transfer(@RequestParam("from") long from,
			@RequestParam("to") long to,
            @RequestParam("amount") float amount, Principal p){
		if(p==null) return null;
		if(from==to) return null;
		
		return walletService.addAmount(from, to, amount, p.getName());
	}
	
	@GetMapping("/{id}/transactions")
	public List<Transaction> getTransactions(@PathVariable("id") long walletId, Principal p){
		if(p==null) return null;
		return walletService.findWalletTransactions(walletId, p.getName());
		
	}
	
	

}
