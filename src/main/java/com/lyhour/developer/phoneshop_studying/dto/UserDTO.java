package com.lyhour.developer.phoneshop_studying.dto;

import com.lyhour.developer.phoneshop_studying.config.security.RoleEnum;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Data;
@Data
public class UserDTO {
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	@Enumerated(EnumType.STRING)
	private RoleEnum role;
	private boolean accountNonExpired;
	private boolean accountNonLocked;
	private boolean credentialsNonExpired;
	private boolean enabled;

}
