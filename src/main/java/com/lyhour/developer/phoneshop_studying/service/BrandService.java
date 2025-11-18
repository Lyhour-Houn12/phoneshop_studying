package com.lyhour.developer.phoneshop_studying.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.entity.Brand;

@Service
public interface BrandService {
	Brand create(Brand brand);
	Brand getById(Integer id);
	Brand update(Integer id, Brand brand);
	//List<Brand> getBrands(Map<String, String> params);
	Page<Brand> pagesBrands(Map<String, String> params);
}
