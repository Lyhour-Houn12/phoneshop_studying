package com.lyhour.developer.phoneshop_studying.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.lyhour.developer.phoneshop_studying.dto.BrandDto;
import com.lyhour.developer.phoneshop_studying.entity.Brand;
@Mapper
public interface BrandMapper {
	BrandMapper INSTANCE = Mappers.getMapper(BrandMapper.class);
	Brand toBrand(BrandDto dto);
	BrandDto toBrandDto(Brand entity);
}
