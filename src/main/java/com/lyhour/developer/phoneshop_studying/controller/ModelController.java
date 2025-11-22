package com.lyhour.developer.phoneshop_studying.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.developer.phoneshop_studying.dto.ModelDTO;
import com.lyhour.developer.phoneshop_studying.entity.Model;
import com.lyhour.developer.phoneshop_studying.mapper.ModelMapper;
import com.lyhour.developer.phoneshop_studying.service.ModelService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("models")
@RequiredArgsConstructor
public class ModelController {
	private final ModelService modelService;
	private final ModelMapper modelMapper;
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ModelDTO modelDto) {
		Model model = modelMapper.toModel(modelDto);
		modelService.save(model);
		return ResponseEntity.ok(modelMapper.toModelDto(model));
		
	}
}
