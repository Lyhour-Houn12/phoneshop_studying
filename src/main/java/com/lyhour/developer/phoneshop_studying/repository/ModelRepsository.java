package com.lyhour.developer.phoneshop_studying.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.lyhour.developer.phoneshop_studying.entity.Model;

public interface ModelRepsository extends JpaRepository<Model, Integer>{
	List<Model> findByBrandId(Integer brandId);
}
