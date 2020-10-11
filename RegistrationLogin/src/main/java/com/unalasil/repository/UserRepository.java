package com.unalasil.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unalasil.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	
	public Optional<User> findByEmail(String email);

	public Optional<User> findByName(String name);
	
		
}
