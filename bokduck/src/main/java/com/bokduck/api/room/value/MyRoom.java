package com.bokduck.api.room.value;

import com.bokduck.common.CodeType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public enum MyRoom implements CodeType {

	Y("내글보기"),
	N("");

	private final String description;

	@Override
	public String getCode() {
		return this.name();
	}

}