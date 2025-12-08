package com.lyhour.developer.phoneshop_studying.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ExpenseReportDTO {
	private Long productId;
	private String productName;
	private Integer unit;
	private BigDecimal totalPrice;
}
