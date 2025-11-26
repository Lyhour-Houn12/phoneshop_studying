package com.lyhour.developer.phoneshop_studying.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lyhour.developer.phoneshop_studying.entity.Color;
@Repository
public interface ColorRepository extends JpaRepository<Color, Long>{

}
