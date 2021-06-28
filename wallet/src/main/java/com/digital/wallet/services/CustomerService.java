package com.digital.wallet.services;

import com.digital.wallet.models.Customer;

public interface CustomerService {
	public Customer findById(long id);
	public Customer findByEmail(String email);
	

}
