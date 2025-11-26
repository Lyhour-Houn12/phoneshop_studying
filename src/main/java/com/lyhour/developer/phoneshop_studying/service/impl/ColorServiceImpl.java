package com.lyhour.developer.phoneshop_studying.service.impl;

import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.entity.Color;
import com.lyhour.developer.phoneshop_studying.exception.ResourceNotFoundOrNot;
import com.lyhour.developer.phoneshop_studying.repository.ColorRepository;
import com.lyhour.developer.phoneshop_studying.service.ColorService;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class ColorServiceImpl implements ColorService{
	private final ColorRepository colorRepository;
	@Override
	public Color save(Color color) {
		return colorRepository.save(color);
	}
	@Override
	public Color getById(Long id) {
		return colorRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundOrNot("Color", id));
	}

}
