package com.lyhour.developer.phoneshop_studying.entity;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Table(name = "roles")
@Entity
public class Role {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "role_id")
	private Long id;
	@Column(name = "role_admin")
	private String name;
	@Column(name = "role_permission")
	@ManyToMany(fetch = FetchType.EAGER)
	Set<Permission> permissions;
}
