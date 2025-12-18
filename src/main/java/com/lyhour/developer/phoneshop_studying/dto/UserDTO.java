package com.lyhour.developer.phoneshop_studying.dto;

import java.util.Set;

import lombok.Data;
@Data
public class UserDTO {
	private String firstname;
	private String lastname;
	private String username;
	private String password;
	private Set<Long> roleIds;
	

}
