package com.bokduck.api.room.application.dto;

import com.bokduck.api.room.domain.RoomCategory;
import com.bokduck.api.room.value.TransactionType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "거래유형 수정")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomCategoryUpdateDto {

	@Schema(description = "거래유형 번호")
	private Long categoryNo;

	@Schema(description = "거래유형")
	private TransactionType transactionType;

	@Schema(description = "보증금")
	private Long depositPrice;

	@Schema(description = "월세")
	private Long monthlyPrice;

	@Schema(description = "방번호")
	private Long roomNo;

	public static RoomCategoryUpdateDto of(RoomCategory category) {
		return RoomCategoryUpdateDto.builder()
				.transactionType(category.getTransactionType())
				.depositPrice(category.getDepositPrice())
				.monthlyPrice(category.getMonthlyPrice())
				.roomNo(category.getRoomNo())
				.build();
	}

}
