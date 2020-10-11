package com.unalasil.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.unalasil.model.User;
import com.unalasil.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public User saveUser(User user) {
		
		String pwd = user.getPassword();
		String encpPwd = passwordEncoder.encode(pwd);
		user.setPassword(encpPwd);
		User createdUser = userRepository.save(user);
		return createdUser;
	}
	
	public User fetchUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}
}
