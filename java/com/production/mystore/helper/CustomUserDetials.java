package com.production.mystore.helper;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.production.mystore.entites.Role;

@Component
public class CustomUserDetials implements UserDetails {

	private String email;
	private String password;
	private List<Role> roles;
	
	
	public CustomUserDetials() {
		super();
		// TODO Auto-generated constructor stub
	}

	public CustomUserDetials(String email, String password, List<Role> roles) {
		super();
		this.email = email;
		this.password = password;
		this.roles = roles;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return roles.stream().map(role -> new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return email;
	}

}
