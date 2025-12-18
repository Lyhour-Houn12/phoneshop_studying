//package com.lyhour.developer.phoneshop_studying.service;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyList;
//import static org.mockito.Mockito.when;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import com.lyhour.developer.phoneshop_studying.dto.ExpenseReportDTO;
//import com.lyhour.developer.phoneshop_studying.entity.Product;
//import com.lyhour.developer.phoneshop_studying.entity.ProductImportHistory;
//import com.lyhour.developer.phoneshop_studying.helper.ReportServiceHelper;
//import com.lyhour.developer.phoneshop_studying.repository.ProductImportRepository;
//import com.lyhour.developer.phoneshop_studying.repository.ProductRepository;
//import com.lyhour.developer.phoneshop_studying.repository.SaleDetailRepository;
//import com.lyhour.developer.phoneshop_studying.service.impl.ReportServiceImpl;
//import com.lyhour.developer.phoneshop_studying.spec.ProductImportHistorySpec;
//
//@ExtendWith(MockitoExtension.class)
//public class ReportServiceTest {
//	private ReportService reportService;
//	@Mock
//	private SaleDetailRepository saleDetailRepository;
//	@Mock
//	private ProductRepository productRepository;
//	@Mock
//	private ProductImportRepository productImportRepository;
//
//	@BeforeEach
//	void setup() {
//		this.reportService = new ReportServiceImpl(saleDetailRepository, productRepository, productImportRepository);
//	}
//
//	@Test
//	void testGetByExpenseReportDTO() {
//		// given
//		List<ProductImportHistory> productImportHistories = ReportServiceHelper.getProductImportHistories();
//		List<Product> products = ReportServiceHelper.getProducts();
//		// when
//		when(productImportRepository.findAll(Mockito.any(ProductImportHistorySpec.class)))
//				.thenReturn(productImportHistories);
//		when(productRepository.findAllById(any())).thenReturn(products);
//		List<ExpenseReportDTO> reportDTO = reportService.getByExpenseReportDTO(LocalDate.now().minusMonths(1),
//				LocalDate.now());
//		// then
//		assertEquals(2, reportDTO.size());
//
//	}
//}
