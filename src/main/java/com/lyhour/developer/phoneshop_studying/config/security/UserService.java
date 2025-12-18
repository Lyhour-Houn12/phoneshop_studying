package com.lyhour.developer.phoneshop_studying.config.security;

import java.util.Optional;

import org.springframework.stereotype.Service;

@Service
public interface UserService {
	Optional<AuthUser> findUserByUsername(String username);
}
