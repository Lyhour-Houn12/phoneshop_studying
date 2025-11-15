package com.lyhour.developer.phoneshop_studying.exception;

import org.springframework.http.HttpStatus;

public class ResourceNotFoundOrNot extends ApiException{
	public ResourceNotFoundOrNot(String reourceName, Integer id) {
		super(HttpStatus.NOT_FOUND, String.format("%s with id = % d not found", reourceName, id));
	}

}
