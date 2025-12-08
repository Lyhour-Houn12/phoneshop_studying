package com.lyhour.developer.phoneshop_studying.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.lyhour.developer.phoneshop_studying.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product>{
	Optional<Product> findByModelIdAndColorId(Long modelId, Long colorId);
}
