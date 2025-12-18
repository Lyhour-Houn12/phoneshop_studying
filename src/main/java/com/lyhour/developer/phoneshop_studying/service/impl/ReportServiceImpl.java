package com.lyhour.developer.phoneshop_studying.service.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.dto.ExpenseReportDTO;
import com.lyhour.developer.phoneshop_studying.dto.ProductReportDTO;
import com.lyhour.developer.phoneshop_studying.entity.Product;
import com.lyhour.developer.phoneshop_studying.entity.ProductImportHistory;
import com.lyhour.developer.phoneshop_studying.entity.SaleDetails;
import com.lyhour.developer.phoneshop_studying.projection.ProductSold;
import com.lyhour.developer.phoneshop_studying.repository.ProductImportRepository;
import com.lyhour.developer.phoneshop_studying.repository.ProductRepository;
import com.lyhour.developer.phoneshop_studying.repository.SaleDetailRepository;
import com.lyhour.developer.phoneshop_studying.service.ReportService;
import com.lyhour.developer.phoneshop_studying.spec.ProductImportHistoryFilter;
import com.lyhour.developer.phoneshop_studying.spec.ProductImportHistorySpec;
import com.lyhour.developer.phoneshop_studying.spec.SaleDetailsFilter;
import com.lyhour.developer.phoneshop_studying.spec.SaleDetailsSpecification;

import lombok.RequiredArgsConstructor;
import lombok.val;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService{
	private final SaleDetailRepository saleDetailRepository;
	private final ProductRepository productRepository;
	private final ProductImportRepository productImportRepository;

	@Override
	public List<ProductSold> getByProductSold(LocalDate startDate, LocalDate endDate) {
		List<ProductSold> productSold = saleDetailRepository.findByProductSold(startDate, endDate);
		return productSold;
	}

	@Override
	public List<ProductReportDTO> getByProductReportDTOs(LocalDate startDate, LocalDate endDate) {
		List<ProductReportDTO> list = new ArrayList<>();
		
		SaleDetailsFilter detailsFilter = new SaleDetailsFilter();
		detailsFilter.setStartDate(startDate);
		detailsFilter.setEndDate(endDate);
		SaleDetailsSpecification spec = new SaleDetailsSpecification(detailsFilter);
		
		List<SaleDetails> saleDetails = saleDetailRepository.findAll(spec);
		List<Long> productIds = saleDetails.stream()
			.map(sd -> sd.getProduct().getId())
			.toList();
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(Product::getId, Function.identity()));
		
		
		
		Map<Product, List<SaleDetails>> saleDetailsMap = saleDetails.stream()
			.collect(Collectors.groupingBy(SaleDetails::getProduct));
		
		for(var entry : saleDetailsMap.entrySet()) {
			Product product = productMap.get(entry.getKey().getId());
			List<SaleDetails> sdLists = entry.getValue();
			// total unit
			Integer unit = sdLists.stream()
				.map(sd -> sd.getUnit())
				.reduce(0, (a,b) -> a+b);
			// total amount of unit
			double totalAmount = sdLists.stream()
				.mapToDouble(sd -> sd.getSoldAmount().doubleValue() * sd.getUnit())
				.sum();
			
			ProductReportDTO reportDTO = new ProductReportDTO();
			reportDTO.setProductId(product.getId());
			reportDTO.setProductName(product.getName());
			reportDTO.setUnit(unit);
			reportDTO.setTotalAmount(BigDecimal.valueOf(totalAmount));
			list.add(reportDTO);
		}
		return list;
	}

	@Override
	public List<ExpenseReportDTO> getByExpenseReportDTO(LocalDate startDate, LocalDate endDate) {
		List<ExpenseReportDTO> list = new ArrayList<>();
		ProductImportHistoryFilter historyFilter = new ProductImportHistoryFilter();
		historyFilter.setStartDate(startDate);
		historyFilter.setEndDate(endDate);
		ProductImportHistorySpec spec = new ProductImportHistorySpec(historyFilter);
		
		List<ProductImportHistory> importHistories = productImportRepository.findAll(spec);
		List<Long> productIds = importHistories.stream()
			.map(his -> his.getProduct().getId())
			.toList();
		List<Product> products = productRepository.findAllById(productIds);
		Map<Long, Product> productMap = products.stream()
			.collect(Collectors.toMap(p -> p.getId(), Function.identity()));
		Map<Product, List<ProductImportHistory>> importHistoryMap = importHistories.stream()
			.collect(Collectors.groupingBy(his -> his.getProduct()));
		
		for(var entry : importHistoryMap.entrySet()) {
			Product product = productMap.get(entry.getKey().getId());
			List<ProductImportHistory> impList = entry.getValue();
			// total unit
			int unit = impList.stream()
				.mapToInt(sd -> sd.getImportUnit())
				.sum();
			double totalPrice = impList.stream()
				.mapToDouble(sd -> sd.getPricePerUnit().doubleValue() * sd.getImportUnit())
				.sum();
			
			ExpenseReportDTO expenseReportDTO = new ExpenseReportDTO();
			expenseReportDTO.setProductId(product.getId());
			expenseReportDTO.setProductName(product.getName());
			expenseReportDTO.setUnit(unit);
			expenseReportDTO.setTotalPrice(BigDecimal.valueOf(totalPrice));
			list.add(expenseReportDTO);
				
			
		}
	
		return list;
	}

}
