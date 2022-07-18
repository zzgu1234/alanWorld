package com.bokduck.api.room.value;

import com.bokduck.common.CodeType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@RequiredArgsConstructor
public enum RoomType implements CodeType {

	ON("원룸"),
	TW("투룸"),
	TH("쓰리룸");

	private final String description;

	@Override
	public String getCode() {
		return this.name();
	}
}
