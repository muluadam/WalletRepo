package com.digital.wallet.services.impl;

import java.util.Arrays;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.digital.wallet.modelRequests.RegisterRequest;
import com.digital.wallet.models.Customer;
import com.digital.wallet.models.Wallet;
import com.digital.wallet.repositories.CustomerRepository;
import com.digital.wallet.services.CustomerService;
import com.digital.wallet.utils.EmailConstructor;
import com.digital.wallet.validators.EmailValidator;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepo;
	
	@Autowired
	private EmailConstructor emailConstructor;
	
	@Override
	public Customer findById(long id) {
		// TODO Auto-generated method stub
		
		return customerRepo.findById(id).get();
	}

	@Override
	public Customer findByEmail(String email) {
		// TODO Auto-generated method stub
		return customerRepo.findByEmail(email);
	}

	

}
