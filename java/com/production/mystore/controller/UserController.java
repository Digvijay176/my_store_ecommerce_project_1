package com.production.mystore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.production.mystore.entites.User;
import com.production.mystore.service.UserService;

import jakarta.validation.Valid;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	//save user
	@PostMapping("/user")
	public ResponseEntity<?> saveUser(@Valid @RequestBody User user){
		ResponseEntity<?> re=new ResponseEntity(userService.saveUser(user),HttpStatus.ACCEPTED);
	return re;
	}
	
	//update user
	@PatchMapping("/user/{id}")
	public ResponseEntity<?> updateUser(@PathVariable int id,@RequestBody User user){
		User user2 = userService.updateUser(id, user);
		return new ResponseEntity(user2,HttpStatus.ACCEPTED);
	}
	
}
