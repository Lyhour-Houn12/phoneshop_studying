package com.lyhour.developer.phoneshop_studying.service.impl;

import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.entity.Model;
import com.lyhour.developer.phoneshop_studying.repository.ModelRepsository;
import com.lyhour.developer.phoneshop_studying.service.ModelService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ModelServiceImpl implements ModelService{
	private final ModelRepsository modelRepsository;
	
	@Override
	public Model save(Model model) {
		return modelRepsository.save(model);
	}

}
