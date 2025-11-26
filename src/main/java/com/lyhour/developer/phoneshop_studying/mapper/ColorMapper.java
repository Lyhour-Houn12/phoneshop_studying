package com.lyhour.developer.phoneshop_studying.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.lyhour.developer.phoneshop_studying.dto.ColorDTO;
import com.lyhour.developer.phoneshop_studying.entity.Color;

@Mapper
public interface ColorMapper {
	ColorMapper INSTANCE = Mappers.getMapper(ColorMapper.class);
	Color toColor(ColorDTO dto);
	ColorDTO toColorDto(Color color);
}
