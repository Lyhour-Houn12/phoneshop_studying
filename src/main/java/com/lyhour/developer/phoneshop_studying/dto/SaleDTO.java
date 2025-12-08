package com.lyhour.developer.phoneshop_studying.dto;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class SaleDTO {
	@NotNull
	List<ProductSoldDTO> products;
	//@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime saleDate;
}
