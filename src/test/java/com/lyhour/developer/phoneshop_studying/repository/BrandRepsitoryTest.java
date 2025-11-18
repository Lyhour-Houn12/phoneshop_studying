package com.lyhour.developer.phoneshop_studying.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.lyhour.developer.phoneshop_studying.entity.Brand;
@DataJpaTest
public class BrandRepsitoryTest {
	@Autowired
	private BrandRepository brandRepository;
	@Test
	public void testfindByNameLike() {
		// given 
		Brand brand = new Brand();
		brand.setName("Apple");
		Brand brand2 = new Brand();
		brand2.setName("SAmsung");
		brandRepository.save(brand);
		brandRepository.save(brand2);
		// when
		List<Brand> nameLike = brandRepository.findByNameLike("%A%");
		
		// then
		assertEquals(2, nameLike.size());
		List<String> name = nameLike.stream()
			.map(Brand::getName)
			.toList();
		assertTrue(name.contains("Apple"));
		assertTrue(name.contains("SAmsung"));
	}
	@Test
	public void testfindByNameIgnoreCase() {
		// given
		Brand brand = new Brand();
		brand.setName("apple");
		brandRepository.save(brand);
		// when
		List<Brand> byNameIgnoreCase = brandRepository.findByNameIgnoreCase("Apple");
		// then
		assertEquals(1, byNameIgnoreCase.size());
		assertEquals("apple", byNameIgnoreCase.get(0).getName());
		
	}
}
