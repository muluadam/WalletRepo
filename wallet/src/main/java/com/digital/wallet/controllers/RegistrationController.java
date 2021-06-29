package com.digital.wallet.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digital.wallet.modelRequests.ConfirmationTokenAndAddPin;
import com.digital.wallet.modelRequests.RegisterRequest;
import com.digital.wallet.services.ConfirmationTokenService;
import com.digital.wallet.services.RegistrationService;

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
