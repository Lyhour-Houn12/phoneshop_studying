package com.lyhour.developer.phoneshop_studying.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.developer.phoneshop_studying.dto.UserDTO;
import com.lyhour.developer.phoneshop_studying.entity.User;
import com.lyhour.developer.phoneshop_studying.mapper.UserMapper;
import com.lyhour.developer.phoneshop_studying.service.UserService;

import lombok.RequiredArgsConstructor;

@RequestMapping("users")
@RestController
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	@PostMapping
	public ResponseEntity<?> create(@RequestBody UserDTO userDTO){
		User user = UserMapper.INSTANCE.toUser(userDTO);
		user  = userService.createUser(user);
		return ResponseEntity.ok(user);
	}
	
}
