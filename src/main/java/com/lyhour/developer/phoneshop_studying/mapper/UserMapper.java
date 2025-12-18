package com.lyhour.developer.phoneshop_studying.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.lyhour.developer.phoneshop_studying.dto.UserDTO;
import com.lyhour.developer.phoneshop_studying.entity.User;

@Mapper
public interface UserMapper {
	UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
	@Mapping(target = "id", ignore = true)
	User toUser(UserDTO userDTO);
}
