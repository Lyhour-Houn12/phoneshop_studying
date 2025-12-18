package com.lyhour.developer.phoneshop_studying.entity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor

@Table(name = "products",
uniqueConstraints = {@UniqueConstraint(columnNames = {"model_id", "color_id"})} )
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "product_id")
	private Long id;
	@Column(name = "product_name", unique = true)
	private String name;
	@Column(name = "product_img")
	private String image_path;
	@Column(name = "product_available")
	private Integer availableUnit;
	@Column(name = "sale_price")
	private BigDecimal salePrice;
	@ManyToOne
	@JoinColumn(name = "color_id")
	private Color color;
	@ManyToOne
	@JoinColumn(name = "model_id")
	private Model model;
}
