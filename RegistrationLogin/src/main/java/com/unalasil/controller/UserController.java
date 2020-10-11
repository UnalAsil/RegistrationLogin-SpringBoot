package com.unalasil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.unalasil.model.User;
import com.unalasil.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/admin")
	public String admin() {
		return "<h2>Welcome Admin!</h2>";
	}
	
	@GetMapping("/user")
	public String user() {

		return "<h2>Welcome User!</h2>";
	}
	
	@GetMapping("/")
	public String All() {
		return "<h2>Welcome all!</h2>";
	}
	
	
	@PostMapping("/admin/register")
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
	
	@GetMapping("/admin/register")
	public Page<User> listUserss(Pageable pageable){
		return userService.listUsers(pageable);
	}
	
	

}
