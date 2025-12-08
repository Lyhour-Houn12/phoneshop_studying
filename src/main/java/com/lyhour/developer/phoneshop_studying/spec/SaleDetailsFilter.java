package com.lyhour.developer.phoneshop_studying.spec;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SaleDetailsFilter {
	private LocalDate startDate;
	private LocalDate endDate;
	
}
