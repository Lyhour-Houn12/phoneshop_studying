package com.lyhour.developer.phoneshop_studying.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyhour.developer.phoneshop_studying.entity.ProductImportHistory;

public interface ProductImportRepository extends JpaRepository<ProductImportHistory, Long>{

}
