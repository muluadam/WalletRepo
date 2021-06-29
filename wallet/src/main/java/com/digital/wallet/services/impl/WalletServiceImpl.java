package com.digital.wallet.services.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.digital.wallet.enums.Status;
import com.digital.wallet.modelRequests.CardInfo;
import com.digital.wallet.models.Card;
import com.digital.wallet.models.Customer;
import com.digital.wallet.models.Transaction;
import com.digital.wallet.models.Wallet;
import com.digital.wallet.repositories.TansactionRepository;
import com.digital.wallet.repositories.WalletRepository;
import com.digital.wallet.services.BanckService;
import com.digital.wallet.services.CardService;
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
	@Autowired
	private BanckService banckService;
	@Autowired
	private CardService cardService;

	@Override
	public ResponseEntity<String> transferAmount(long from, long toTag, float amount, String userEmail) {
		Wallet w2 = walletRepo.findByWalletTag(toTag);

		if (w2 == null)
			return error("Wallet Tag : " + toTag + " not found");
		if (checkCustomerHasWallet(from, userEmail)==null)
			return error("Wrong Wallet Id");
		Wallet w1 = walletRepo.findById(from);

		if (w1 != null) {
			Transaction t = new Transaction(amount, w1.getWalletId(), w2.getWalletId(), LocalDateTime.now());
			if (w1.getAmount() < amount) {
				t.setStatus(Status.FAILED);
				transactionRepo.save(t);
				return error("Not enough money in your wallet");
			}

			w2.setAmount(w2.getAmount() + amount);
			w1.setAmount(w1.getAmount() - amount);
			t.setStatus(Status.SUCCESS);
			transactionRepo.save(t);
			walletRepo.save(w1);
			walletRepo.save(w2);
			return new ResponseEntity<>("Success", HttpStatus.OK);
		}
		return error("Wrong WalletId");
	}

	@Override
	public ResponseEntity<?> findWalletTransactions(long walletId, String email) {

		if (checkCustomerHasWallet(walletId, email)==null)
			return error("you are Trying to access wrong wallet id :"+walletId);
		
		return new ResponseEntity<>(transactionRepo.findWalletTransactions(walletId), HttpStatus.OK);
	}

	private Customer checkCustomerHasWallet(long walletId, String email) {
		Customer c = customerService.findByEmail(email);
		if(c != null) {
		boolean hisWallet = false;
		for (Wallet w : c.getWallets()) {
			if (w.getWalletId() != walletId)
				hisWallet = false;
			else {
				hisWallet = true;
				break;
			}
			}
		if(hisWallet) return c;
		else return null;
		}
		return c;
	}

	@Override
	public ResponseEntity<String> topUpMoney(long walletId, CardInfo cardInfo, float amount, String email) {
		Customer customer = checkCustomerHasWallet(walletId, email);
		if (customer==null)
			return error("Wrong wallet Id ");

		String date = cardInfo.getExpareDate() + "-01";
		LocalDate d = null;
		try {
			d = LocalDate.parse(date);
		} catch (DateTimeParseException e) {
			// Throw invalid date message
			return error("invalide expary date for your card");

		}
		Card card = new Card(cardInfo.getCardNumber(), cardInfo.getCsv(), d);

		switch (banckService.validAndEnoughMoney(amount, card)) {
		case -1:
			return error("Invalid Card ");

		case 2:
			card.setCardHolder(customer);
			Wallet w = walletRepo.findById(walletId);
			w.setAmount(w.getAmount() + amount);
			cardService.save(card);
			walletRepo.save(w);
			return new ResponseEntity<>("Your card was debited seccussfly", HttpStatus.OK);

		case -2:
			return error("Not Enough Money on the card ");

		default:
			return error("Card Invalide");
		}

	}

	private ResponseEntity<String> error(String msgError) {
		return new ResponseEntity<>(msgError, HttpStatus.BAD_REQUEST);
	}

}
