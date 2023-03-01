package com.app.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.blog.entity.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
	Optional<Role> findByName(String name);
}
