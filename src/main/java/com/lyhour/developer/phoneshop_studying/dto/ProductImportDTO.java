package com.lyhour.developer.phoneshop_studying.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ProductImportDTO {
	private Long productId;
	private Integer importUnit;
	private BigDecimal importPrice;
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")	
	private LocalDateTime importDateTime;
}
