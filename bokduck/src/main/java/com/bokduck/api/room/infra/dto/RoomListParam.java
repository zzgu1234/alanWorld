package com.bokduck.api.room.infra.dto;

import javax.validation.constraints.NotNull;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomListParam {

	private String roomType;
	private String transactionType;
	private String priceType;
	private Long min;
	private Long max;
	private String id;

	@Builder
	public RoomListParam(@NotNull String roomType,
			@NotNull String transactionType,
			@NotNull String priceType,
			Long min,
			Long max,
			@NotNull String id) {

		this.roomType = roomType;
		this.transactionType = transactionType;
		this.priceType = priceType;
		this.min = min;
		this.max = max;
		this.id = id;

	}

}
