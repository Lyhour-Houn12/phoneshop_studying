package com.lyhour.developer.phoneshop_studying.service.impl;

import java.math.BigDecimal;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.dto.ProductImportDTO;
import com.lyhour.developer.phoneshop_studying.entity.Product;
import com.lyhour.developer.phoneshop_studying.entity.ProductImportHistory;
import com.lyhour.developer.phoneshop_studying.exception.ApiException;
import com.lyhour.developer.phoneshop_studying.exception.ResourceNotFoundOrNot;
import com.lyhour.developer.phoneshop_studying.mapper.ProductMapper;
import com.lyhour.developer.phoneshop_studying.repository.ProductImportRepository;
import com.lyhour.developer.phoneshop_studying.repository.ProductRepository;
import com.lyhour.developer.phoneshop_studying.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
	private final ProductRepository productRepository;
	private final ProductImportRepository importRepository;
	private final ProductMapper mapper;
	@Override
	public Product save(Product product) {
		String name = "%s %s".formatted(product.getModel().getName(), product.getColor().getName());
		product.setName(name);
		return productRepository.save(product);
	}
	@Override
	public Product getbyId(Long id) {
		return productRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundOrNot("Product", id));
	}
	@Override
	public void importProduct(ProductImportDTO importDTO) {
		// update product by import
		if(importDTO.getImportUnit() == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Unit can not be null!");
		}
		Product product = this.getbyId(importDTO.getProductId());
		Integer availableUnit = 0;
		if(product.getAvailableUnit() != null) {
			availableUnit = product.getAvailableUnit();
		}
		product.setAvailableUnit(availableUnit + importDTO.getImportUnit());
		productRepository.save(product);
		// save import product
		ProductImportHistory productImportHistory = mapper.toProductImportHistory(importDTO, product);
		importRepository.save(productImportHistory);
		
	}
	@Override
	public void setSalePrice(Long productId, BigDecimal price) {
		Product product = getbyId(productId);
		product.setSalePrice(price);
		productRepository.save(product);
		
	}
	

}
