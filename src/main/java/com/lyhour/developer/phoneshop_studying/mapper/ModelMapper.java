package com.lyhour.developer.phoneshop_studying.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.lyhour.developer.phoneshop_studying.dto.ModelDTO;
import com.lyhour.developer.phoneshop_studying.entity.Model;
import com.lyhour.developer.phoneshop_studying.service.BrandService;

@Mapper(componentModel = "spring",uses = {BrandService.class})
public interface ModelMapper {
	ModelMapper INSTANCE = Mappers.getMapper(ModelMapper.class);
	
	@Mapping(target = "brand", source = "brandId")
	Model toModel(ModelDTO dto);
	@Mapping(target = "brandId", source = "brand.id")
	ModelDTO toModelDto(Model model);
}
