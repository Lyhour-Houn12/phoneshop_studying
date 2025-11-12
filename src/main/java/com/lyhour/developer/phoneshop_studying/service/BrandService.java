package com.lyhour.developer.phoneshop_studying.service;

import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.entity.Brand;

@Service
public interface BrandService {
	Brand create(Brand brand);
	
}
