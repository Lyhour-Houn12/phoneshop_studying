package com.lyhour.developer.phoneshop_studying.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.lyhour.developer.phoneshop_studying.dto.ProductDTO;
import com.lyhour.developer.phoneshop_studying.dto.ProductImportDTO;
import com.lyhour.developer.phoneshop_studying.entity.Product;
import com.lyhour.developer.phoneshop_studying.entity.ProductImportHistory;
import com.lyhour.developer.phoneshop_studying.service.ColorService;
import com.lyhour.developer.phoneshop_studying.service.ModelService;

@Mapper(componentModel = "spring", uses = {ModelService.class, ColorService.class})
public interface ProductMapper {
	
	
	@Mapping(target = "model", source = "modelId")
	@Mapping(target = "color", source = "colorId")
	Product toProduct(ProductDTO dto);
	
	
	@Mapping(target = "importUnit", source = "dto.importUnit")
	@Mapping(target = "pricePerUnit", source = "dto.importPrice")
	@Mapping(target = "importDateTime", source = "dto.importDateTime")
	@Mapping(target = "product", source = "product")
	@Mapping(target = "id", ignore = true)
	ProductImportHistory toProductImportHistory(ProductImportDTO dto, Product product);
	
	

}
