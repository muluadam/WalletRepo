package com.digital.wallet.controllers;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.digital.wallet.jwtUtils.JwtTokenProvider;
import com.digital.wallet.modelRequests.LoginRequest;
import com.digital.wallet.modelResponses.JwtResponse;
import com.digital.wallet.services.CustomerService;


@RestController
@CrossOrigin
public class CustomerController {
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private CustomerService customerService;
	
	
	
	@GetMapping("/")
	public String  accueil() {
		return "Welcome Page";
	}
	@GetMapping("/hello")
	public String  seyHello(Principal p) {
		if(p==null) return null;
		return "You are authentivated";
	}
	
	
	@PostMapping("/login")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody LoginRequest authenticationRequest) throws Exception {

		final ResponseEntity<String> response = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());
		if(response!=null) return response;
		
		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenProvider.generateToken(userDetails);

		return ResponseEntity.ok(new JwtResponse(token));
	}

	private ResponseEntity<String> authenticate(String username, String password) throws Exception {
		try {
		
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
			
		} catch (DisabledException e) {
			return new ResponseEntity<>("Please confirm your registration",HttpStatus.BAD_REQUEST);
			
		} catch (BadCredentialsException e) {
			return new ResponseEntity<>("Bad credantials",HttpStatus.BAD_REQUEST);
		} 
		return null;
	}
	
	
	
	
	
//	@GetMapping(value = "/profile")
	
	

}
