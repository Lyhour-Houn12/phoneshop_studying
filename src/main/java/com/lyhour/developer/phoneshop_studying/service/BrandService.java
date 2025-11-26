package com.lyhour.developer.phoneshop_studying.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.entity.Brand;

@Service
public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Long id);
	Brand update(Long id, Brand brand);
	//List<Brand> getBrands(Map<String, String> params);
	Page<Brand> pagesBrands(Map<String, String> params);
}
