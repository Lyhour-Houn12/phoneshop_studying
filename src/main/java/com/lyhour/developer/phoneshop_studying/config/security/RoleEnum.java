package com.lyhour.developer.phoneshop_studying.config.security;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public enum RoleEnum {
	ADMIN(Set.of(PermissionEnum.BRANE_WRITE, PermissionEnum.BRAND_READ, PermissionEnum.MODEL_WRITE, PermissionEnum.MODEL_READ)),
	SALE(Set.of(PermissionEnum.BRAND_READ, PermissionEnum.MODEL_READ));
	
	Set<PermissionEnum> permissions;
	public Set<SimpleGrantedAuthority> authorities(){
		Set<SimpleGrantedAuthority> authority = this.permissions.stream()
			.map(permision -> new SimpleGrantedAuthority(permision.getDescription()))
			.collect(Collectors.toSet());
		SimpleGrantedAuthority role = new SimpleGrantedAuthority("ROLE_"+ this.name());
		authority.add(role);
		
		return authority;
	}
	 }
	
	
	

