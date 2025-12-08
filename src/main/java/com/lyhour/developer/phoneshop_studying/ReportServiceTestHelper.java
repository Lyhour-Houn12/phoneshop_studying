package com.lyhour.developer.phoneshop_studying;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.lyhour.developer.phoneshop_studying.entity.Product;
import com.lyhour.developer.phoneshop_studying.entity.ProductImportHistory;

public class ReportServiceTestHelper {
	private static Product product1 = Product.builder()
			.id(1l)
			.name("iphone 14 pro max")
			.build();
	private static Product product2 = Product.builder()
			.id(2l)
			.name("iphone 13 pro max")
			.build();
	private static Product product3 = Product.builder()
			.id(3l)
			.name("iphone 12 pro max")
			.build();
	
	public static List<ProductImportHistory> getProductImportHistories(){
		ProductImportHistory history1 = ProductImportHistory.builder()
				.product(product1)
				.importUnit(10)
				.pricePerUnit(BigDecimal.valueOf(1200))
				.importDateTime(LocalDateTime.of(2025, 12, 7, 3, 23))
				.build();
		ProductImportHistory history2 = ProductImportHistory.builder()
				.product(product1)
				.importUnit(15)
				.pricePerUnit(BigDecimal.valueOf(1100))
				.importDateTime(LocalDateTime.of(2025, 12, 8, 5, 23))
				.build();
		ProductImportHistory history3 = ProductImportHistory.builder()
				.product(product1)
				.importUnit(10)
				.pricePerUnit(BigDecimal.valueOf(1000))
				.importDateTime(LocalDateTime.of(2025, 12, 8, 10, 23))
				.build();		
		return List.of(history1, history2, history3);
	}
}
