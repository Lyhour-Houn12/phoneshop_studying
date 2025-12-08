package com.lyhour.developer.phoneshop_studying.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.developer.phoneshop_studying.dto.ExpenseReportDTO;
import com.lyhour.developer.phoneshop_studying.dto.ProductReportDTO;
import com.lyhour.developer.phoneshop_studying.projection.ProductSold;
import com.lyhour.developer.phoneshop_studying.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("reports")
@RequiredArgsConstructor
public class ReportController {
	private final ReportService reportService;
	@GetMapping("v1/{startDate}/{endDate}")
	public ResponseEntity<?> productSold(@DateTimeFormat(pattern =  "yyyy-MM-dd") @PathVariable LocalDate startDate,@DateTimeFormat(pattern =  "yyyy-MM-dd") @PathVariable LocalDate endDate){
		List<ProductSold> productSold = reportService.getByProductSold(startDate, endDate);
		return ResponseEntity.ok(productSold);
	}
	@GetMapping("{startDate}/{endDate}")
	public ResponseEntity<?> productReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate startDate, @DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate endDate){
		List<ProductReportDTO> productReportDTOs = reportService.getByProductReportDTOs(startDate, endDate);
		return ResponseEntity.ok(productReportDTOs);
	}

	@GetMapping("expense/{startDate}/{endDate}")
	public ResponseEntity<?> exspenseReport(@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate startDate,@DateTimeFormat(pattern = "yyyy-MM-dd") @PathVariable LocalDate endDate ){
		List<ExpenseReportDTO> expenseReportDTO = reportService.getByExpenseReportDTO(startDate, endDate);		
		return ResponseEntity.ok(expenseReportDTO);
	}
	

}
