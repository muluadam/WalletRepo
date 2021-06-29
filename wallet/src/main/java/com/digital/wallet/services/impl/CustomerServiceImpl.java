package com.digital.wallet.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.digital.wallet.models.Customer;
import com.digital.wallet.repositories.CustomerRepository;
import com.digital.wallet.services.CustomerService;
@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired
	private CustomerRepository customerRepo;
	
	
	@Override
	public Customer findById(long id) {
		return customerRepo.findById(id);
	}

	@Override
	public Customer findByEmail(String email) {
		return customerRepo.findByEmail(email);
	}

	

}
