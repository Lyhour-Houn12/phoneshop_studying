package com.lyhour.developer.phoneshop_studying.service;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.lyhour.developer.phoneshop_studying.entity.Brand;
import com.lyhour.developer.phoneshop_studying.exception.ResourceNotFoundOrNot;
import com.lyhour.developer.phoneshop_studying.repository.BrandRepository;
import com.lyhour.developer.phoneshop_studying.service.impl.BrandServiceImpl;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {
	@Mock
	private BrandRepository brandRepository;
	
	private BrandService brandService;
	@BeforeEach
	public void setUp() {
		this.brandService = new BrandServiceImpl(brandRepository);
	}
	/*
	@Test
	public void testCreate() {
		// given
		Brand brand = new Brand();
		brand.setName("Apple");
		brand.setId(1);
		// when
		when(brandRepository.save(any(Brand.class))).thenReturn(brand);
		Brand brandReturn = brandService.create(new Brand());	
		// then
		assertEquals("Apple", brandReturn.getName());
		assertEquals(1, brandReturn.getId());
	}
	*/
	@Test
	public void testCreate() {
		// given
		Brand brand = new Brand();
		brand.setId(1);
		brand.setName("Apple");
		// when
		brandService.create(brand);
		// then
		verify(brandRepository, timeout(1)).save(brand);
	}
	@Test
	public void testGetByIdSuccess() {
		// given
		Brand brand = new  Brand();
		brand.setName("Apple");
		brand.setId(1);
		// when
		when(brandRepository.findById(1)).thenReturn(Optional.of(brand));
		Brand brandReturn = brandService.getById(brand.getId());
		// then
		assertEquals(1, brandReturn.getId());
		assertEquals("Apple", brandReturn.getName());
	}
	@Test
	public void testGetByIdFail() {
		// given
		
		// when
		when(brandRepository.findById(1)).thenReturn(Optional.empty());
		assertThatThrownBy(() -> brandService.getById(1))
			.isInstanceOf(ResourceNotFoundOrNot.class)
			.hasMessage("Brand with id = 1 not found");
	}
	
	
	
	
}
