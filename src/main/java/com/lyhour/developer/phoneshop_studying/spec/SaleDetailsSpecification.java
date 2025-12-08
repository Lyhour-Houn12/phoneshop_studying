package com.lyhour.developer.phoneshop_studying.spec;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.data.jpa.domain.Specification;

import com.lyhour.developer.phoneshop_studying.entity.Sale;
import com.lyhour.developer.phoneshop_studying.entity.SaleDetails;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SaleDetailsSpecification implements Specification<SaleDetails>{
	private SaleDetailsFilter saleDetailsFilter;
	
	@Override
	public Predicate toPredicate(Root<SaleDetails> saleDetails, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = new ArrayList<>();
		Join<SaleDetails, Sale> sale = saleDetails.join("sale");
		if(Objects.nonNull(saleDetailsFilter.getStartDate())) {
			//saleDetails.get("soldDate");
			cb.greaterThanOrEqualTo(sale.get("soldDate"), saleDetailsFilter.getStartDate());
		}
		if(Objects.nonNull(saleDetailsFilter.getEndDate())) {
			cb.lessThanOrEqualTo(sale.get("soldDate"), saleDetailsFilter.getEndDate());
		} 
		return cb.and(predicates.toArray(Predicate[]::new));
	
	}

}
