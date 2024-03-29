package com.bokduck.api.room.value;

import com.bokduck.common.CodeType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public enum TransactionType implements CodeType {

	C("전세"),
	M("월세");

	private final String description;

	@Override
	public String getCode() {
		return this.name();
	}

}
