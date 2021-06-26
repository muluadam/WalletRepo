package com.digital.wallet.modelResponses;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;

public class JwtResponse {
	private final String token;
	private int id;
	private String username;
	private Collection<? extends GrantedAuthority> roles;

	public JwtResponse(int id, String username,Collection<? extends GrantedAuthority> collection, String token ) {
		this.token = token;
		this.id=id;
		this.username=username;
		this.roles=collection;
	}



	public int getId() {
		return id;
	}



	public String getUsername() {
		return username;
	}

	

	

	

	public Collection<? extends GrantedAuthority> getRoles() {
		return roles;
	}



	



	public String getToken() {
		return token;
	}

}
