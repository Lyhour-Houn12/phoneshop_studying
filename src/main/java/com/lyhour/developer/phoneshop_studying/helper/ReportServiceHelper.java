//package com.lyhour.developer.phoneshop_studying.helper;
//
//import java.math.BigDecimal;
//import java.time.LocalDateTime;
//import java.util.List;
//
//import com.lyhour.developer.phoneshop_studying.entity.Product;
//import com.lyhour.developer.phoneshop_studying.entity.ProductImportHistory;
//
//public class ReportServiceHelper {
//	private static Product product1 = Product.builder()
//			.id(1l)
//			.name("iphone 14 pro")
//			.build();
//	private static Product product2 = Product.builder()
//			.id(2l)
//			.name("iphone 12 pro")
//			.build();
//	private static Product product3 = Product.builder()
//			.id(3l)
//			.name("iphone 11 pro")
//			.build();
//	public static List<Product> getProducts() {
//		return List.of(product1, product2);
//	}
//	public static List<ProductImportHistory> getProductImportHistories(){
//		ProductImportHistory history1 = ProductImportHistory.builder()
//				.product(product1)
//				.importUnit(20)
//				.importDateTime(LocalDateTime.of(2026, 12, 7, 10, 0))
//				.pricePerUnit(BigDecimal.valueOf(1200))
//				.build();
//		ProductImportHistory history2 = ProductImportHistory.builder()
//				.product(product2)
//				.importUnit(10)
//				.importDateTime(LocalDateTime.of(2026, 12, 10, 10, 22))
//				.pricePerUnit(BigDecimal.valueOf(1100))
//				.build();
//		ProductImportHistory history3 = ProductImportHistory.builder()
//				.product(product3)
//				.importUnit(110)
//				.importDateTime(LocalDateTime.of(2026, 12, 7, 9, 12))
//				.pricePerUnit(BigDecimal.valueOf(1000))
//				.build();
//		
//		return List.of(history1, history2, history3);
//	}
//}
