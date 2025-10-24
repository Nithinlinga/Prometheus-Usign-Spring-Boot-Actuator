package com.cts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.cts.entity.User;
import com.cts.service.UserService;

import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService service;
	
//	public UserController(UserService service) {
//		this.service=service;
//	}
	
//	@PostMapping
//	public User register(@RequestBody User user) {
//		return service.register(user);
//	}
	
	@PostMapping
	public ResponseEntity<User> register(@RequestBody User user){
		
		user= service.register(user);
		return new ResponseEntity<>(user,HttpStatus.CREATED);
		
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody User user){
		
		String token= service.login(user);
		return new ResponseEntity<>(token,HttpStatus.CREATED);
		
	}
	@GetMapping
	public ResponseEntity<List<User>> login(){

		List<User> allUsers= service.getAllUsers();
		return new ResponseEntity<>(allUsers,HttpStatus.CREATED);

	}
}
