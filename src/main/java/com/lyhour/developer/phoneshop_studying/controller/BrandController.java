package com.lyhour.developer.phoneshop_studying.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.developer.phoneshop_studying.dto.BrandDTO;
import com.lyhour.developer.phoneshop_studying.entity.Brand;
import com.lyhour.developer.phoneshop_studying.mapper.BrandMapper;
import com.lyhour.developer.phoneshop_studying.service.BrandService;


@RestController
@RequestMapping("brands")
public class BrandController {
	@Autowired
	private BrandService brandService;
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDTO brandDTO) {
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.create(brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrand(brandDTO));
	}
	@GetMapping("{id}")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer brandId){
		Brand brand = brandService.getById(brandId);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));
	}
	@PutMapping("{id}")
	public ResponseEntity<?> updated(@PathVariable("id") Integer branId, @RequestBody BrandDTO brandDTO){
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		Brand updatedBrand = brandService.update(branId, brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(updatedBrand));
	}
}
