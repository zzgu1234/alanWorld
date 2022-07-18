package com.bokduck.api.room.value;

import com.bokduck.common.CodeType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public enum PriceType implements CodeType {

	D("보증금"),
	M("월세");

	private final String description;

	@Override
	public String getCode() {
		return this.name();
	}

}
