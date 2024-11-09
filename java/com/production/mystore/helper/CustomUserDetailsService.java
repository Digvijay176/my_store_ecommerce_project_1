package com.production.mystore.helper;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.production.mystore.entites.User;
import com.production.mystore.repository.UserRepository;

public class CustomUserDetailsService implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<User> user = userRepository.findByEmail(email);
		if(user.isEmpty()) {
			   throw new UsernameNotFoundException("User not found with email: " + email);
		}
		User newUser= user.get();
		return new CustomUserDetials(newUser.getEmail(),newUser.getPassword(),newUser.getRoles());
	}

}
