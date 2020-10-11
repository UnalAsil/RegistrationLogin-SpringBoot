package com.unalasil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.unalasil.model.User;
import com.unalasil.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/register")
	public User registerUser(@RequestBody User user) throws Exception {
		String tempEmail = user.getEmail();
		
		if(tempEmail != null && !"".equals(tempEmail)) {
			if(userService.fetchUserByEmail(tempEmail)!=null) {
				throw new Exception("User with " + tempEmail + " is already exist");
			}
		}
		
		User createdUser = userService.saveUser(user);
		return createdUser;
	}

	
}
