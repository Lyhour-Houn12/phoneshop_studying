package com.lyhour.developer.phoneshop_studying.service.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.config.security.AuthUser;
import com.lyhour.developer.phoneshop_studying.entity.User;
import com.lyhour.developer.phoneshop_studying.exception.ResourceNotFoundOrNot;
import com.lyhour.developer.phoneshop_studying.repository.UserRepository;
import com.lyhour.developer.phoneshop_studying.service.UserService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
@Primary
public class UserDetailServiceImpl implements UserDetailsService, UserService{
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User users = userRepository.findByUsername(username)
			.orElseThrow(() -> new ResourceNotFoundOrNot("Username", null));
		AuthUser authUser = AuthUser.builder()
			.username(users.getUsername())
			.password(users.getPassword())
			.authorities(users.getRole().authorities())
			.accountNonExpired(users.isAccountNonExpired())
			.accountNonLocked(users.isAccountNonLocked())
			.credentialsNonExpired(users.isCredentialsNonExpired())
			.enabled(users.isEnabled())
			.build();
		return authUser;
	}

	@Override
	public User createUser(User user) {
		String passEncoder = passwordEncoder.encode(user.getPassword());
		user.setPassword(passEncoder);
		return userRepository.save(user);
	}

	

}
