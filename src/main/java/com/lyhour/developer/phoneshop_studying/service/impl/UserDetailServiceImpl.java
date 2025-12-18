package com.lyhour.developer.phoneshop_studying.service.impl;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.context.annotation.Primary;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.config.security.AuthUser;
import com.lyhour.developer.phoneshop_studying.entity.Role;
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
			.orElseThrow(() -> new UsernameNotFoundException("User not found: "+ username));
		AuthUser authUser = AuthUser.builder()
			.username(users.getUsername())
			.password(users.getPassword())
			.authorities(getAuthorities(users.getRoles()))
			.accountNonExpired(users.isAccountNonExpired())
			.accountNonLocked(users.isAccountNonLocked())
			.credentialsNonExpired(users.isCredentialsNonExpired())
			.enabled(users.isEnabled())
			.build();
		return authUser;
	}
	private Set<SimpleGrantedAuthority> getAuthorities(Set<Role> roles){
		Set<SimpleGrantedAuthority> authority = roles.stream()
			.map(role -> new SimpleGrantedAuthority("ROLE_" + role.getName()))
			.collect(Collectors.toSet());
		Set<SimpleGrantedAuthority> authorities = roles.stream()
			.flatMap(role -> toStream(role))
			.collect(Collectors.toSet());
		authorities.addAll(authority);
		return authorities;
	}
	private Stream<SimpleGrantedAuthority> toStream(Role role){
		return role.getPermissions().stream()
			.map(permission -> new SimpleGrantedAuthority(permission.getName()));
	}
	
	@Override
	public User createUser(User user) {
		String passEncoder = passwordEncoder.encode(user.getPassword());
		user.setPassword(passEncoder);
		return userRepository.save(user);
	}

	

}
