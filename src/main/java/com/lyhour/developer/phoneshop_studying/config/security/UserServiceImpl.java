package com.lyhour.developer.phoneshop_studying.config.security;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.entity.User;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
	
	private final PasswordEncoder passwordEncoder;	
	@Override
	public Optional<AuthUser> findUserByUsername(String username) {
		List<AuthUser> authUsers = List.of(
					new AuthUser("nhanha", passwordEncoder.encode("nha123"), RoleEnum.ADMIN.authorities(), true, true, true, true),
					new AuthUser("thida", passwordEncoder.encode("thida123"), RoleEnum.SALE.authorities(), true, true, true, true)
				);
		Optional<AuthUser> getUsers = authUsers.stream()
			.filter(user -> user.getUsername().equals(username))
			.findFirst();		
		return getUsers;
	}


	

}
