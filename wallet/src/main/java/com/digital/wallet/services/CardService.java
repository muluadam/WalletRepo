package com.digital.wallet.services;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.digital.wallet.modelRequests.CardInfo;
import com.digital.wallet.models.Card;
import com.digital.wallet.models.Customer;
import com.digital.wallet.repositories.CardRepository;

@Service
public class CardService {
	@Autowired
	private CardRepository cardRepo;
	@Autowired
	private BanckService banckService;

	

	public ResponseEntity<String> chackAndAddCard(CardInfo cardInfo, Customer customer) {
		
		String date =cardInfo.getExpareDate()+"-01";
		//handle parse error exception here
		Card card = new Card(cardInfo.getCardNumber(),cardInfo.getCsv(),LocalDate.parse(date));
		if(banckService.findAndCheckValidity(card) != null) {
			card.setCardHolder(customer);
			cardRepo.save(card);
			return new ResponseEntity<>("Card valide",HttpStatus.OK);
		}
		return new ResponseEntity<>("Card Invalide",HttpStatus.BAD_REQUEST);
	}
	public void save(Card card) {
		cardRepo.save(card);
		
	}
	

	
	
}
