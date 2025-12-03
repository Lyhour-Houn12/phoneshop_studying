package com.lyhour.developer.phoneshop_studying.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lyhour.developer.phoneshop_studying.dto.SaleDTO;
import com.lyhour.developer.phoneshop_studying.service.SaleService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("sales")
@RequiredArgsConstructor
public class SaleController {
	private final SaleService saleService;
	
	@PostMapping
	public ResponseEntity<?> sale(@RequestBody SaleDTO saleDTO){
		saleService.sell(saleDTO);
		return ResponseEntity.ok().build();
	}

}
