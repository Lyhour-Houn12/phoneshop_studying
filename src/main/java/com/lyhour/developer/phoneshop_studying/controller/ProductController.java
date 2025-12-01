package com.lyhour.developer.phoneshop_studying.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.developer.phoneshop_studying.dto.PriceDTO;
import com.lyhour.developer.phoneshop_studying.dto.ProductDTO;
import com.lyhour.developer.phoneshop_studying.dto.ProductImportDTO;
import com.lyhour.developer.phoneshop_studying.entity.Product;
import com.lyhour.developer.phoneshop_studying.mapper.ProductMapper;
import com.lyhour.developer.phoneshop_studying.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	private final ProductMapper productMapper;
	
	@PostMapping
	public ResponseEntity<?> create(@RequestBody ProductDTO productDTO){
		Product product = productMapper.toProduct(productDTO);
		product = productService.save(product);
		return ResponseEntity.ok(product);
	}
	@PostMapping("importProduct")
	public ResponseEntity<?> importProduct(@RequestBody ProductImportDTO importDTO){
		productService.importProduct(importDTO);
		return ResponseEntity.ok().build();
	}
	@PostMapping("{productId}/setSalePrice")
	public ResponseEntity<?> setSalePrice(@PathVariable Long productId, @RequestBody PriceDTO priceDTO){
		productService.setSalePrice(productId, priceDTO.getPrice());
		return ResponseEntity.ok().build();
	}
	

}
