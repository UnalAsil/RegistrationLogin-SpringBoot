package com.unalasil.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.unalasil.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

}
