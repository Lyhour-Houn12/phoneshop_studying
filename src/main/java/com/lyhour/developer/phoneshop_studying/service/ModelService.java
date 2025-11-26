package com.lyhour.developer.phoneshop_studying.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.entity.Model;

@Service
public interface ModelService {
	Model save(Model model);
	List<Model> getByBrand(Integer BrandId);
	Model getById(Long id);
}
