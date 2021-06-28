package com.digital.wallet.models;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Table(name = "customers")
public class Customer implements UserDetails{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @OneToMany(mappedBy = "walletHolder", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Wallet> wallets;
	
	public Customer( String firstName, String lastName, String email, String password,
			List<Wallet> wallets) {
		super();
		
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.wallets = wallets;
		this.password=password;
	}

	

	public Customer() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
	private int customerPin;
    private Boolean locked = false;
    private Boolean enabled = false;
    
	public Long getId() {
		return id;
	}

	public List<Wallet> getWallets() {
		return wallets;
	}

	public void setWallets(List<Wallet> wallets) {
		this.wallets = wallets;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}


	public int getCustomerPin() {
		return customerPin;
	}

	public void setCustomerPin(int customerPin) {
		this.customerPin = customerPin;
	}
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		 SimpleGrantedAuthority authority =
	                new SimpleGrantedAuthority("USER");
		 
	     return Collections.singletonList(authority);
	
	}
	 @Override
	    public String getPassword() {
	        return password;
	    }

	    @Override
	    public String getUsername() {
	        return email;
	    }

	    public String getFirstName() {
	        return firstName;
	    }

	    public String getLastName() {
	        return lastName;
	    }

	    @Override
	    public boolean isAccountNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isAccountNonLocked() {
	        return !locked;
	    }

	    @Override
	    public boolean isCredentialsNonExpired() {
	        return true;
	    }

	    @Override
	    public boolean isEnabled() {
	        return enabled;
	    }
	    public void setEnabled(boolean enabled) {
	         this.enabled=enabled;
	    }


}
