package com.bokduck.room.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RoomType {

	ON("원룸"),
	TW("투룸"),
	TH("쓰리룸");

	RoomType(String description) {}

}
