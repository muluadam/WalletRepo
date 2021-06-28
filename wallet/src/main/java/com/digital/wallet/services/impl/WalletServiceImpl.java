package com.digital.wallet.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.digital.wallet.enums.Status;
import com.digital.wallet.models.Customer;
import com.digital.wallet.models.Transaction;
import com.digital.wallet.models.Wallet;
import com.digital.wallet.repositories.TansactionRepository;
import com.digital.wallet.repositories.WalletRepository;
import com.digital.wallet.services.CustomerService;
import com.digital.wallet.services.WalletService;

@Service
public class WalletServiceImpl implements WalletService {
	@Autowired
	private WalletRepository walletRepo;
	@Autowired
	private TansactionRepository transactionRepo;
	
	@Autowired
	private CustomerService customerService;

	@Override
	public ResponseEntity<String> addAmount(long from, long to, float amount,String email) {
		// TODO Auto-generated method stub
		if(!checkCustomerHasWallet(from,email)) return new ResponseEntity<>("Wrong WalletId",HttpStatus.BAD_REQUEST);
		Wallet w1 = walletRepo.findById(from).get();
		Wallet w2 = walletRepo.findById(to).get();
		if(w1 != null && w2 != null) {
			Transaction t = new Transaction(amount,w1.getWalletId(),w2.getWalletId(), LocalDateTime.now() );
			if(w1.getAmount()<amount) {
				t.setStatus(Status.FAILED);
				transactionRepo.save(t);
				return new ResponseEntity<>("Not enough money in your wallet",HttpStatus.BAD_REQUEST);
			}
			
			w2.setAmount(w2.getAmount()+amount);
			w1.setAmount(w1.getAmount()-amount);
			t.setStatus(Status.SUCCESS);
			transactionRepo.save(t);
			walletRepo.save(w1);
			walletRepo.save(w2);
			return new ResponseEntity<>("Done",HttpStatus.OK);
		}
		return new ResponseEntity<>("Wrong WalletId",HttpStatus.BAD_REQUEST);
	}
	
	
	@Override
	public List<Transaction> findWalletTransactions(long walletId, String email) {
		// TODO Auto-generated method stub
		
		if(!checkCustomerHasWallet(walletId,email)) return null;
		return transactionRepo.findWalletTransactions(walletId);
	}
	
	private boolean checkCustomerHasWallet(long walletId,String email) {
		Customer c = customerService.findByEmail(email);
		boolean isUser =false;

		for(Wallet w : c.getWallets()) {
			if(w.getWalletId() != walletId) isUser= false;
			else {isUser=true;
				break;
			}
		}
		return isUser;
	}



}
