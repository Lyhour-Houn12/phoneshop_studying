package com.lyhour.developer.phoneshop_studying.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.dto.ExpenseReportDTO;
import com.lyhour.developer.phoneshop_studying.dto.ProductReportDTO;
import com.lyhour.developer.phoneshop_studying.projection.ProductSold;
@Service
public interface ReportService {
	List<ProductSold> getByProductSold(LocalDate startDate, LocalDate endDate);
	
	List<ProductReportDTO> getByProductReportDTOs(LocalDate startDate, LocalDate endDate);
	
	List<ExpenseReportDTO> getByExpenseReportDTO(LocalDate startDate, LocalDate endDate);
}
