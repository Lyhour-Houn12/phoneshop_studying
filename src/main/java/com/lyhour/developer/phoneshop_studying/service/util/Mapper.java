package com.lyhour.developer.phoneshop_studying.service.util;

import com.lyhour.developer.phoneshop_studying.dto.BrandDTO;
import com.lyhour.developer.phoneshop_studying.entity.Brand;

public class Mapper {
	public static Brand toBrand(BrandDTO dto) {
		Brand brand = new Brand();
		brand.setId(dto.getId());
		brand.setName(dto.getName());
		return brand;
	}
}
