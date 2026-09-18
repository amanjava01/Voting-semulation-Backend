package com.voting_semulation.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.voting_semulation.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(String name);

}
