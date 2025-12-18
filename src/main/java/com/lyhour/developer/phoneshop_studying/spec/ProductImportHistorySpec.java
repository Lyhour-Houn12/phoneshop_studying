package com.lyhour.developer.phoneshop_studying.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.lyhour.developer.phoneshop_studying.entity.ProductImportHistory;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductImportHistorySpec implements Specification<ProductImportHistory>{
	private ProductImportHistoryFilter historyFilter = new ProductImportHistoryFilter();

	@Override
	public Predicate toPredicate(Root<ProductImportHistory> importHistory, CriteriaQuery<?> query,CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		if(Objects.nonNull(historyFilter.getStartDate())) {
			cb.greaterThanOrEqualTo(importHistory.get("importDateTime"), historyFilter.getStartDate());
		}
		if(Objects.nonNull(historyFilter.getEndDate())) {
			cb.lessThanOrEqualTo(importHistory.get("importDateTime"), historyFilter.getEndDate());
		}
		return cb.and(predicates.toArray(Predicate[]::new));

	}
	
}