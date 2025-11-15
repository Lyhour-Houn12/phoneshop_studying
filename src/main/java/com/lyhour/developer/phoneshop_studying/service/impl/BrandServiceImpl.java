package com.lyhour.developer.phoneshop_studying.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.lyhour.developer.phoneshop_studying.entity.Brand;
import com.lyhour.developer.phoneshop_studying.exception.ApiException;
import com.lyhour.developer.phoneshop_studying.exception.ResourceNotFoundOrNot;
import com.lyhour.developer.phoneshop_studying.repository.BrandRepository;
import com.lyhour.developer.phoneshop_studying.service.BrandService;

@Service
public class BrandServiceImpl implements BrandService{
    @Autowired
	private BrandRepository brandRepository;
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
		return brandRepository.findById(id)
				//.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND, String.format("Brand with id = %d not found", id)));
				//.orElseThrow(() -> new ApiException(HttpStatus.NOT_FOUND, String.format("Brand with id = %d not found", id)));
				.orElseThrow(() -> new ResourceNotFoundOrNot("Brand", id));
	}
	@Override
	public Brand update(Integer id, Brand brandUpdate) {
		Brand brand = getById(id);
		brand.setName(brandUpdate.getName());
		return brandRepository.save(brand);
	}
	

}
