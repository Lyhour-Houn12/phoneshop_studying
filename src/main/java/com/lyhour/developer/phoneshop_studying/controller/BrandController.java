	package com.lyhour.developer.phoneshop_studying.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.developer.phoneshop_studying.dto.BrandDto;
import com.lyhour.developer.phoneshop_studying.dto.ModelDTO;
import com.lyhour.developer.phoneshop_studying.dto.PageDto;
import com.lyhour.developer.phoneshop_studying.entity.Brand;
import com.lyhour.developer.phoneshop_studying.entity.Model;
import com.lyhour.developer.phoneshop_studying.mapper.BrandMapper;
import com.lyhour.developer.phoneshop_studying.mapper.ModelEntityMapper;
import com.lyhour.developer.phoneshop_studying.repository.BrandRepository;
import com.lyhour.developer.phoneshop_studying.service.BrandService;
import com.lyhour.developer.phoneshop_studying.service.ModelService;

@RestController
@RequestMapping("brands")
public class BrandController {
	private final BrandRepository brandRepository;
	@Autowired
	private BrandService brandService;
	@Autowired
	private ModelService modelService;

    BrandController(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<?> create(@RequestBody BrandDto brandDTO) {
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		brand = brandService.create(brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrand(brandDTO));
	}
	@GetMapping("{id}/models")
	public ResponseEntity<?> getOneBrand(@PathVariable("id") Integer brandId){
//		Brand brand = brandService.getById(id);
//		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(brand));
		List<Model> brands = modelService.getByBrand(brandId);
		List<ModelDTO> list = brands.stream()
			.map(model -> ModelEntityMapper.INSTANCE.toModelDto(model))
			.toList();
		return ResponseEntity.ok(list);
	}
	@PutMapping("{id}")
	public ResponseEntity<?> updated(@PathVariable Long id, @RequestBody BrandDto brandDTO){
		Brand brand = BrandMapper.INSTANCE.toBrand(brandDTO);
		Brand updatedBrand = brandService.update(id, brand);
		return ResponseEntity.ok(BrandMapper.INSTANCE.toBrandDto(updatedBrand));
	}
	/*
	@GetMapping
	public ResponseEntity<?> getBrands(@RequestParam Map<String, String> params){
		List<BrandDto> listBrands = brandService.getBrands(params)
			.stream()
			.map(brand -> BrandMapper.INSTANCE.toBrandDto(brand))
			.collect(Collectors.toList());
		return ResponseEntity.ok(listBrands);
	}
	*/
	@GetMapping
	public ResponseEntity<?> pageBrands(@RequestParam Map<String, String> params){
		Page<Brand> pagesBrands = brandService.pagesBrands(params);
		PageDto pageDto = new PageDto(pagesBrands);
		return ResponseEntity.ok(pageDto);
	}
	
}