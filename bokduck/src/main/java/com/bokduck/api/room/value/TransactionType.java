package com.bokduck.api.room.value;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionType {

	C("전세"),
	M("월세");

	TransactionType(String description) {

	}

}
