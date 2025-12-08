package com.lyhour.developer.phoneshop_studying.projection;

import java.math.BigDecimal;

public interface ProductSold {
	Long getProductId();
	String getProductName();
	Integer getProductUnit();
	BigDecimal getTotalAmount();
}
