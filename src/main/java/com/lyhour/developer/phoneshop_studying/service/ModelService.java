package com.lyhour.developer.phoneshop_studying.service;

import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.entity.Model;

@Service
public interface ModelService {
	Model save(Model model);
}
