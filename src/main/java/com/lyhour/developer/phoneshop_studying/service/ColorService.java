package com.lyhour.developer.phoneshop_studying.service;

import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.entity.Color;
@Service
public interface ColorService {
	Color save(Color color);
	Color getById(Long id);
}
