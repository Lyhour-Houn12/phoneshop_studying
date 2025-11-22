package com.lyhour.developer.phoneshop_studying.service.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.entity.Brand;
import com.lyhour.developer.phoneshop_studying.exception.ResourceNotFoundOrNot;
import com.lyhour.developer.phoneshop_studying.repository.BrandRepository;
import com.lyhour.developer.phoneshop_studying.service.BrandService;
import com.lyhour.developer.phoneshop_studying.service.util.PageUtil;
import com.lyhour.developer.phoneshop_studying.spec.BrandFilter;
import com.lyhour.developer.phoneshop_studying.spec.BrandSpecification;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BrandServiceImpl implements BrandService{
    @Autowired
	private final BrandRepository brandRepository;
	@Override
	public Brand create(Brand brand) {
		return brandRepository.save(brand);
	}
	@Override
	public Brand getById(Integer id) {
//		Optional<Brand> brandOptional  = brandRepository.findById(id);
//		if(brandOptional.isPresent()) {
//			return brandOptional.get();
//		}
//		throw new HttpClientErrorException(HttpStatus.NOT_ACCEPTABLE, String.format("Brand with id = %d not found", id));
		 Brand brand = brandRepository.findById(id)
				//.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Brand with id = %d not found", id)));
				//.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("Brand with id = %d not found", id)));
				.orElseThrow(() -> new ResourceNotFoundOrNot("Brand", id));
		 return brand;
		 
		
	}
	@Override
	public Brand update(Integer id, Brand brandUpdate) {
		Brand brand = getById(id);
		brand.setName(brandUpdate.getName());    // @TODO improve later
		return brandRepository.save(brand);
	}
	/*
	@Override
	public List<Brand> getBrands(Map<String, String> params) {
		BrandFilter brandFilter = new BrandFilter();
		if(params.containsKey("name")) {
			String name = params.get("name");
			brandFilter.setName(name);
		}
		if(params.containsKey("id")) {
			String id = params.get("id");
			brandFilter.setId(Integer.parseInt(id));
		}
		BrandSpecification brandSpecification = new BrandSpecification(brandFilter);
		
		
		return brandRepository.findAll(brandSpecification);
	}
	*/
	@Override
	public Page<Brand> pagesBrands(Map<String, String> params) {
		BrandFilter brandFilter = new BrandFilter();
		if(params.containsKey("name")) {
			String name = params.get("name");
			brandFilter.setName(name);
		}
		if(params.containsKey("id")) {
			String id = params.get("id");
			brandFilter.setName(id);
		}
		int pageSize = PageUtil.DEFUALT_PAGE_LIMIT;
		if(params.containsKey(PageUtil.PAGE_LIMIT)) {
			pageSize = Integer.parseInt(params.get(PageUtil.PAGE_LIMIT));
		}
		int pageNumber = PageUtil.DEFAULT_PAGE_NUMBER;
		if(params.containsKey(PageUtil.PAGE_NUMBER)) {
			pageNumber = Integer.parseInt(params.get(PageUtil.PAGE_NUMBER));
		}
		BrandSpecification brandSpecification = new BrandSpecification(brandFilter);
		Pageable pageable = PageUtil.getPageable(pageNumber, pageSize);
		Page<Brand> page = brandRepository.findAll(brandSpecification, pageable);
		return page;
	}
	
	

}
