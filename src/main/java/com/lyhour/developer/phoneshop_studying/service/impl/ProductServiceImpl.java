package com.lyhour.developer.phoneshop_studying.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.lyhour.developer.phoneshop_studying.dto.ProductImportDTO;
import com.lyhour.developer.phoneshop_studying.entity.Product;
import com.lyhour.developer.phoneshop_studying.entity.ProductImportHistory;
import com.lyhour.developer.phoneshop_studying.exception.ApiException;
import com.lyhour.developer.phoneshop_studying.exception.ResourceNotFoundOrNot;
import com.lyhour.developer.phoneshop_studying.mapper.ProductMapper;
import com.lyhour.developer.phoneshop_studying.repository.ProductImportRepository;
import com.lyhour.developer.phoneshop_studying.repository.ProductRepository;
import com.lyhour.developer.phoneshop_studying.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	private final ProductRepository productRepository;
	private final ProductImportRepository importRepository;
	private final ProductMapper mapper;

	@Override
	public Product save(Product product) {
		String name = "%s %s".formatted(product.getModel().getName(), product.getColor().getName());
		product.setName(name);
		return productRepository.save(product);
	}

	@Override
	public Product getbyId(Long id) {
		return productRepository.findById(id).orElseThrow(() -> new ResourceNotFoundOrNot("Product", id));
	}

	@Override
	public void importProduct(ProductImportDTO importDTO) {
		// update product by import
		if (importDTO.getImportUnit() == null) {
			throw new ApiException(HttpStatus.BAD_REQUEST, "Unit can not be null!");
		}
		Product product = this.getbyId(importDTO.getProductId());
		Integer availableUnit = 0;
		if (product.getAvailableUnit() != null) {
			availableUnit = product.getAvailableUnit();
		}
		product.setAvailableUnit(availableUnit + importDTO.getImportUnit());
		productRepository.save(product);
		// save import product
		ProductImportHistory productImportHistory = mapper.toProductImportHistory(importDTO, product);
		importRepository.save(productImportHistory);

	}

	@Override
	public void setSalePrice(Long productId, BigDecimal price) {
		Product product = getbyId(productId);
		product.setSalePrice(price);
		productRepository.save(product);

	}

	@Override
	public Product getfindByModelIdAndColorId(Long modelId, Long colorId) {
		String text = "Product with model id = %d and color id = %d was not found";
		return productRepository.findByModelIdAndColorId(modelId, colorId)
				.orElseThrow(() -> new ApiException(HttpStatus.BAD_REQUEST, text.formatted(modelId, colorId)));
	}

	@Override
	public Map<Integer, String> updateProduct(MultipartFile file) {
		Map<Integer, String> map = new HashMap<>();
		try (Workbook workbook = new XSSFWorkbook(file.getInputStream())) {
			Sheet sheet = workbook.getSheet("products_import");
			Iterator<Row> rowIterator = sheet.iterator();
			rowIterator.next();
				while (rowIterator.hasNext()) {
					Integer rowNumber = 0;
					try {
						int cellIndex = 0;
						Row row = rowIterator.next();
						if (row == null || row.getCell(0) == null) continue;
						Cell cellNo = row.getCell(cellIndex++);
						rowNumber = (int) cellNo.getNumericCellValue();
						Cell cellModelId = row.getCell(cellIndex++);
						Long modelId = (long) cellModelId.getNumericCellValue();
						Cell cellColorId = row.getCell(cellIndex++);
						Long colorId = (long) cellColorId.getNumericCellValue();
						Cell cellPriceImport = row.getCell(cellIndex++);
						Double importPrice = cellPriceImport.getNumericCellValue();
						Cell cellUnitImport = row.getCell(cellIndex++);
						Integer importUnit = (int) cellUnitImport.getNumericCellValue();
						if(importUnit < 1) {
							throw new ApiException(HttpStatus.BAD_REQUEST, "Unit must be greater than 0");
						}
						Cell cellImportdate = row.getCell(cellIndex++);
						LocalDateTime importDate = cellImportdate.getLocalDateTimeCellValue();
						Product product = getfindByModelIdAndColorId(modelId, colorId);
						Integer availableUnit = 0;
						if (product.getAvailableUnit() != null) {
							availableUnit = product.getAvailableUnit();
						}
						product.setAvailableUnit(availableUnit + importUnit);
						productRepository.save(product);
						// save import product
						ProductImportHistory productImportHistory = new ProductImportHistory();
						productImportHistory.setImportDateTime(importDate);
						productImportHistory.setImportUnit(importUnit);
						productImportHistory.setPricePerUnit(BigDecimal.valueOf(importPrice));
						productImportHistory.setProduct(product);
						importRepository.save(productImportHistory);
					} catch (Exception e) {
						map.put(rowNumber, e.getMessage());
					}
					
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		return map;

	}

}
