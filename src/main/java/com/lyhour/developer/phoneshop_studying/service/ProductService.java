package com.lyhour.developer.phoneshop_studying.service;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lyhour.developer.phoneshop_studying.dto.ProductImportDTO;
import com.lyhour.developer.phoneshop_studying.entity.Product;
@Service
public interface ProductService {
	Product save(Product product);
	Product getbyId(Long id);
	Product getfindByModelIdAndColorId(Long modelId, Long colorId);
	void importProduct(ProductImportDTO importDTO);
	void setSalePrice(Long productId, BigDecimal price);
	Map<Integer, String> updateProduct(MultipartFile file);
}
