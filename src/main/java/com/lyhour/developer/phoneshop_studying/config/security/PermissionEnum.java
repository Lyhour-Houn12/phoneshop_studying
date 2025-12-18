package com.lyhour.developer.phoneshop_studying.config.security;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public enum PermissionEnum {
	BRANE_WRITE("brand:write"),
	BRAND_READ("brand:read"),
	MODEL_WRITE("model:write"),
	MODEL_READ("model:read");
	
	private String description;
}
