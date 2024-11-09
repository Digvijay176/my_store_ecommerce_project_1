package com.production.mystore.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.production.mystore.entites.User;
import com.production.mystore.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	//add user
	public User saveUser(User user) {
		return userRepository.save(user);
	}

	//update the user
	public User updateUser(int id, User user) {
		Optional<User> u = userRepository.findById(id);
		if (u.isEmpty()) {
			throw new UsernameNotFoundException("user not found!");
		}
		User newUser = u.get();
		newUser.setName(user.getName());
		newUser.setEmail(user.getEmail());
		newUser.setRoles(user.getRoles());
		return userRepository.save(newUser);
	}
}
