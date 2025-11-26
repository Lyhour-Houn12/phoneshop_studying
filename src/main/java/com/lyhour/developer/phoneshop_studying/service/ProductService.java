package com.lyhour.developer.phoneshop_studying.service;

import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.entity.Product;
@Service
public interface ProductService {
	Product save(Product product);
}
