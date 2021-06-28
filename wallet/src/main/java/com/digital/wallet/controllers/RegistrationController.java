package com.digital.wallet.controllers;

import java.time.LocalDateTime;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digital.wallet.modelRequests.ConfirmationTokenAndAddPin;
import com.digital.wallet.modelRequests.RegisterRequest;
import com.digital.wallet.models.ConfirmationToken;
import com.digital.wallet.models.Customer;
import com.digital.wallet.models.Wallet;
import com.digital.wallet.repositories.CustomerRepository;
import com.digital.wallet.services.ConfirmationTokenService;
import com.digital.wallet.services.CustomerService;
import com.digital.wallet.services.RegistrationService;
import com.digital.wallet.utils.EmailConstructor;
import com.digital.wallet.validators.EmailValidator;

@RestController
public class RegistrationController {
	
	
	@Autowired
	private RegistrationService registrationService;
	@Autowired
	private ConfirmationTokenService confirmationTokenService;
	
	
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest req) {
		
		return registrationService.register(req);
		
	}
	

	
	@PostMapping("/registration/confirm")
	public ResponseEntity<String> confirmToken(@RequestBody ConfirmationTokenAndAddPin tokenAndPin) {
		
		return confirmationTokenService.verifyToken(tokenAndPin.getToken(),tokenAndPin.getPin());
		
		
	}
}
