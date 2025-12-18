package com.lyhour.developer.phoneshop_studying.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyhour.developer.phoneshop_studying.entity.User;

public interface UserRepository extends JpaRepository<User, Long>{
	Optional<User> findByUsername(String username);
}
