package com.lyhour.developer.phoneshop_studying.service;

import com.lyhour.developer.phoneshop_studying.dto.SaleDTO;
import com.lyhour.developer.phoneshop_studying.entity.Sale;

public interface SaleService {
	void sell(SaleDTO saleDTO);
	Sale getById(Long saleId);
	void cancelSale(Long saleId);
}
