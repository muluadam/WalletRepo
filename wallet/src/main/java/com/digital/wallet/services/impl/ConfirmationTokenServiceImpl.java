package com.digital.wallet.services.impl;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.digital.wallet.models.ConfirmationToken;
import com.digital.wallet.models.Customer;
import com.digital.wallet.repositories.ConfirmationTokenRepository;
import com.digital.wallet.repositories.CustomerRepository;
import com.digital.wallet.services.ConfirmationTokenService;
import com.digital.wallet.utils.EmailConstructor;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {
	@Autowired
	private ConfirmationTokenRepository confirmationTokenRepository;
	@Autowired
	private EmailConstructor emailSender;
	@Autowired
	private CustomerRepository customerRepository;

	public void save(ConfirmationToken token) {
		confirmationTokenRepository.save(token);
	}

	@Override
	public ResponseEntity<String> verifyToken(String token) {
		// TODO Auto-generated method stub

		ConfirmationToken t = confirmationTokenRepository.findByToken(token);
		if (t == null)
			return new ResponseEntity<>("Invalide token", HttpStatus.BAD_REQUEST);
		LocalDateTime expiredAt = t.getExpiresAt();
		Customer c = t.getCustomer();
		if (expiredAt.isBefore(LocalDateTime.now())) {
			ConfirmationToken newToken = new ConfirmationToken(LocalDateTime.now(),LocalDateTime.now().plusHours(10),c);
			emailSender.send(c.getFirstName()+" "+c.getLastName(), c.getEmail(), newToken.getToken());
			confirmationTokenRepository.save(newToken);
			confirmationTokenRepository.delete(t);
			return new ResponseEntity<>("token expired, an email was sent to verify your account",
					HttpStatus.BAD_REQUEST);
		}
		else {
			c.setEnabled(true);
			customerRepository.save(c);
			confirmationTokenRepository.delete(t);
		}

		return new ResponseEntity<>("account verified",
				HttpStatus.OK);
	}

	/*
	 * public int setConfirmedAt(String token) { return
	 * confirmationTokenRepository.updateConfirmedAt( token, LocalDateTime.now()); }
	 */
}
