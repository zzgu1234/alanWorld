package com.bokduck.api.room.application.dto;

import com.bokduck.api.room.value.TransactionType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "거래유형 등록")
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomCategoryCreateDto {

	@Schema(description = "거래 유형")
	private TransactionType transactionType;

	@Schema(description = "보증금")
	private Long depositPrice;

	@Schema(description = "월세")
	private Long monthlyPrice;

}
