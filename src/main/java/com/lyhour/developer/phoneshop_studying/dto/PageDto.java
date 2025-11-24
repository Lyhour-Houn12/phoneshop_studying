package com.lyhour.developer.phoneshop_studying.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import lombok.Data;
@Data
public class PageDto {
	private List<?> list;
	private Pagination pagination;
	public PageDto(Page<?> page) {
		this.list = page.getContent();
		this.pagination = pagination.builder()
				.empty(page.isEmpty())
				.first(page.isFirst())
				.last(page.isLast())
				.pageSize(page.getPageable().getPageSize())
				.pageNumber(page.getPageable().getPageNumber() + 1)
				.numberOfElements(page.getNumberOfElements())
				.totalPages(page.getTotalPages())				
				.totalElements(page.getTotalElements())
				.build();
	}
}
