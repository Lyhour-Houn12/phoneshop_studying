package com.lyhour.developer.phoneshop_studying.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.entity.Brand;

@Service
public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer id);
	Brand update(Integer id, Brand brand);
	List<Brand> getBrands(String name);
	
}
