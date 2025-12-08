package com.lyhour.developer.phoneshop_studying.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@AllArgsConstructor
@Entity
@NoArgsConstructor
@Table(name = "productImportHistory")
public class ProductImportHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "import_id")
	private Long id;
	@Column(name =  "import_unit")
	private Integer importUnit;
	@Column(name = "import_price")
	private BigDecimal pricePerUnit;
	@Column(name = "import_date")
	private LocalDateTime importDateTime;
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;
}
