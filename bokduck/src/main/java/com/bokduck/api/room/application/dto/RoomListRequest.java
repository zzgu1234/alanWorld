package com.bokduck.api.room.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "방 목록 파라미터")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomListRequest {

	/**
	 * 방 유형
	 */
	private String roomType;

	/**
	 * 거래 유형
	 */
	private String transactionType;

	/*
	 * 금액 유형
	 */
	private String priceType;

	/*
	 * 최소 가격범위
	 */
	private Long min;

	/*
	 * 최대 가격범위
	 */
	private Long max;

	/*
	 * 내글 여부
	 */
	private String myRoom;

}
