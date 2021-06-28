package com.digital.wallet.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.digital.wallet.models.Customer;
import com.digital.wallet.repositories.CustomerRepository;

@Service
public class CustomerDetailsService implements UserDetailsService{

	@Autowired
	private CustomerRepository customerRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		Customer customer = customerRepository.findByEmail(username);
		if(customer==null) {
			throw new UsernameNotFoundException("Customer not found with email: " + username);
		}
		
//		boolean accountNonExpired = true; 
//		boolean credentialsNonExpired = true; 
//		boolean accountNonLocked = true;
//		
//		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
//				username, 
//				customer.getPassword(), 
//				true, 
//				accountNonExpired, 
//				credentialsNonExpired, 
//				accountNonLocked, 
//				Arrays.asList(new SimpleGrantedAuthority("USER")));
		
		return customer;
	}
	
	
	


}