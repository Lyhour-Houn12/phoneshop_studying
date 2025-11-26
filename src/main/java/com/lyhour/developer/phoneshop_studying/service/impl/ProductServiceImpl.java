package com.lyhour.developer.phoneshop_studying.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.entity.Product;
import com.lyhour.developer.phoneshop_studying.repository.ProductRepository;
import com.lyhour.developer.phoneshop_studying.service.ProductService;
@Service
public class ProductServiceImpl implements ProductService{
	@Autowired
	ProductRepository productRepository;
	@Override
	public Product save(Product product) {
		String name = "%s %s".formatted(product.getModel().getName(), product.getColor().getName());
		product.setName(name);
		return productRepository.save(product);
	}

}
