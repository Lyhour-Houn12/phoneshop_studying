package com.lyhour.developer.phoneshop_studying.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.developer.phoneshop_studying.dto.ColorDTO;
import com.lyhour.developer.phoneshop_studying.entity.Color;
import com.lyhour.developer.phoneshop_studying.mapper.ColorMapper;
import com.lyhour.developer.phoneshop_studying.service.ColorService;

import lombok.RequiredArgsConstructor;
@RestController
@RequiredArgsConstructor
@RequestMapping("colors")
public class ColorController {
	private final ColorService colorService;
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ColorDTO colorDTO){
		Color color = ColorMapper.INSTANCE.toColor(colorDTO);
		color = colorService.save(color);
		return ResponseEntity.ok(ColorMapper.INSTANCE.toColorDto(color));
	}
	@GetMapping("{id}")
	public ResponseEntity<?> getByColor(@PathVariable ("id") Long ColorId){
		Color color = colorService.getById(ColorId);
		return ResponseEntity.ok(ColorMapper.INSTANCE.toColorDto(color));
	}
}
