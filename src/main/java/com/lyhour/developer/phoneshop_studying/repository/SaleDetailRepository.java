package com.lyhour.developer.phoneshop_studying.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.lyhour.developer.phoneshop_studying.entity.SaleDetails;
import com.lyhour.developer.phoneshop_studying.projection.ProductSold;

public interface SaleDetailRepository extends JpaRepository<SaleDetails, Long>, JpaSpecificationExecutor<SaleDetails>{
	List<SaleDetails> findBySaleId(Long saleId);
	
	@Query(value = "select p.product_id as productId,"
			+ " p.product_name as productName,"
			+ " sum(sd.sale_unit) as productUnit,"
			+ " sum(sd.sale_unit * sd.sold_amount) as totalAmount from sale_details sd\r\n"
			+ "inner join sales s on sd.sale_id = s.sale_id\r\n"
			+ "inner join products p on p.product_id = sd.product_id\r\n"
			+ "where date(s.sold_date) >= :startDate and date(s.sold_date) <= :endDate\r\n"
			+ "Group by p.product_id, p.product_name", nativeQuery = true)
	List<ProductSold> findByProductSold(LocalDate startDate, LocalDate endDate);
}