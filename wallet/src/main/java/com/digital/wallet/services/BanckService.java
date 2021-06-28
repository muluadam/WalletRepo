package com.digital.wallet.services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import com.digital.wallet.models.Card;

public class BanckService {
	private static List<Card> CARDS = Arrays.asList(
			new Card(123456789,123, LocalDate.of(2020, 7, 0)),
			new Card(987654321,321, LocalDate.of(2022, 12, 0)),
			new Card(1234560987,132, LocalDate.of(2025, 3, 0)),
			new Card(125678798,132, LocalDate.of(2023, 3, 0)),
			new Card(1231456789,123, LocalDate.of(2020, 7, 0)),
			new Card(987165421,321, LocalDate.of(2022, 12, 0)),
			new Card(1234150987,132, LocalDate.of(2025, 3, 0)),
			new Card(125617798,132, LocalDate.of(2023, 3, 0))
	);
	
	
	public boolean validateCard(Card card) {
		for(Card c : CARDS) {
			if(c.getCardNumber()==card.getCardNumber() )
				if( c.getCardCSV()==card.getCardCSV())
					if(c.getExpiryDate().isBefore(LocalDate.now())) 
						return true;
		}
		return false;
	}
	

}
