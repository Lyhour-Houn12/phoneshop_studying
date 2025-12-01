package com.lyhour.developer.phoneshop_studying.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.dto.ProductImportDTO;
import com.lyhour.developer.phoneshop_studying.entity.Product;
@Service
public interface ProductService {
	Product save(Product product);
	Product getbyId(Long id);
	void importProduct(ProductImportDTO importDTO);
	void setSalePrice(Long productId, BigDecimal price);
}
