package com.lyhour.developer.phoneshop_studying.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class Pagination {
	private int pageNumber;
	private int pageSize;
	private int numberOfElements;
	private Long totalElements;
	private int totalPages;
	private boolean empty;
	private boolean first;
	private boolean last;
}
