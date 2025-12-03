package com.lyhour.developer.phoneshop_studying.service.impl;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.dto.ProductSoldDTO;
import com.lyhour.developer.phoneshop_studying.dto.SaleDTO;
import com.lyhour.developer.phoneshop_studying.entity.Product;
import com.lyhour.developer.phoneshop_studying.entity.Sale;
import com.lyhour.developer.phoneshop_studying.entity.SaleDetails;
import com.lyhour.developer.phoneshop_studying.exception.ApiException;
import com.lyhour.developer.phoneshop_studying.repository.ProductRepository;
import com.lyhour.developer.phoneshop_studying.repository.SaleDetailRepository;
import com.lyhour.developer.phoneshop_studying.repository.SaleRepository;
import com.lyhour.developer.phoneshop_studying.service.ProductService;
import com.lyhour.developer.phoneshop_studying.service.SaleService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService{

	private final ProductService productService;
	private final ProductRepository productRepository;
	private final SaleRepository saleRepository;
	private final SaleDetailRepository saleDetailRepository;
	@Override
	public void sell(SaleDTO saleDTO) {
		List<Long> productIds = saleDTO.getProducts().stream()
			.map(ProductSoldDTO::getProductId)
			.toList();
		// validate product
		productIds.forEach(productService::getbyId);
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		// validate stock
		saleDTO.getProducts()
			.forEach(ps ->{
				Product product = productMap.get(ps.getProductId());
				if(product.getAvailableUnit() < ps.getNumberOfUnit()) {
					throw new ApiException(HttpStatus.BAD_REQUEST, "Product [%s] is not enough in stock.".formatted(product.getName()));
				}
			});
		// save sale
		Sale sale = new Sale();
		sale.setSoldDate(saleDTO.getSaleDate());
		saleRepository.save(sale);
		
		// save saleDetailz
		saleDTO.getProducts().forEach(ps ->{
			Product product = productMap.get(ps.getProductId());
			SaleDetails saleDetails = new SaleDetails();
			saleDetails.setSoldAmount(product.getSalePrice());
			saleDetails.setProduct(product);
			saleDetails.setSale(sale);
			saleDetails.setUnit(ps.getNumberOfUnit());
		
		// cut in stock
			Integer availableUnit = product.getAvailableUnit() - ps.getNumberOfUnit();
			product.setAvailableUnit(availableUnit);
			saleDetailRepository.save(saleDetails);
			productRepository.save(product);
			
		});
		
		
	}

}
