package com.digital.wallet.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
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
			throw new UsernameNotFoundException("Customer not found with username: " + username);

		}
		
		boolean accountNonExpired = true; 
		boolean credentialsNonExpired = true; 
		boolean accountNonLocked = true;
		
		UserDetails userDetails = new org.springframework.security.core.userdetails.User(
				username, 
				customer.getPassword(), 
				true, 
				accountNonExpired, 
				credentialsNonExpired, 
				accountNonLocked, 
				Arrays.asList(new SimpleGrantedAuthority("USER")));
		
		return userDetails;
	}
	
	
	

//	private Collection<? extends GrantedAuthority> getAuthorities(List<Role> roles) {
//		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
//		for (Role role: roles) {
//			GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(role.getName());
//			grantedAuthorities.add(grantedAuthority);
//		}
//		return grantedAuthorities;
//	}

}