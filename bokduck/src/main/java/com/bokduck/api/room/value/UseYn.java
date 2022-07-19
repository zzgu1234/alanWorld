package com.bokduck.api.room.value;

import com.bokduck.common.CodeType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public enum UseYn implements CodeType {

	Y("사용중"),
	N("삭제됨");

	private final String description;

	@Override
	public String getCode() {
		return this.name();
	}

}
