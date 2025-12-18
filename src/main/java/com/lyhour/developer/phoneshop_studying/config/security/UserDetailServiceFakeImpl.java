package com.lyhour.developer.phoneshop_studying.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
@Service
public class UserDetailServiceFakeImpl implements UserDetailsService{
	@Autowired
	private UserService userService;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userService.findUserByUsername(username)
			.orElseThrow(() -> new UsernameNotFoundException("User not found"));
		
	}

}
