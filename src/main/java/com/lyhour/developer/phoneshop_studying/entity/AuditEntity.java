package com.lyhour.developer.phoneshop_studying.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import jakarta.persistence.MappedSuperclass;


@MappedSuperclass
public abstract class AuditEntity {
	@CreatedDate
	private LocalDateTime dateCreate;
	@LastModifiedDate
	private LocalDateTime dateUpdate;
	@CreatedBy
	private String userCreate;
	@LastModifiedBy
	private String userUpdate;
}
