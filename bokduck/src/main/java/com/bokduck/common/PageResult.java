package com.bokduck.common;

import java.util.ArrayList;
import java.util.List;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Singular;

@Getter
@NoArgsConstructor
public class PageResult<T> {

	@Singular
	@Setter
	private List<T> items = new ArrayList<>();

	@Builder
	public PageResult(List<T> items) {
		    this.items = items;
	  }

}
