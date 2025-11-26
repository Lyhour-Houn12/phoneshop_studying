package com.lyhour.developer.phoneshop_studying.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lyhour.developer.phoneshop_studying.entity.Model;

@Repository
public interface ModelRepsository extends JpaRepository<Model, Long>{
	List<Model> findByBrandId(Integer brandId);
}
