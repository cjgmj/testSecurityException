package com.cjgmj.testSecurityException.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cjgmj.testSecurityException.entity.UserEntity;
import com.cjgmj.testSecurityException.service.IUserService;

@RestController
@RequestMapping
public class UserController {

	@Autowired
	private IUserService userService;

	@GetMapping
	public List<UserEntity> findAll() {
		return userService.findAll();
	}

	@GetMapping("/find/{username}")
	public UserEntity findByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}

	@PostMapping("/register")
	public UserEntity registerUser(@RequestBody UserEntity user) {
		return userService.registerUser(user);
	}

}
