package com.bokduck.api.room.application.dto;

import com.bokduck.api.room.infra.RoomResult;
import com.bokduck.api.room.value.RoomType;
import com.bokduck.api.room.value.TransactionType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoomDto {

	/*
	 * Room
	 */
	@Schema(description = "방 번호")
	private Long roomNo;

	@Schema(description = "방 제목")
	private String roomTitle;

	@Schema(description = "방 위치")
	private String roomLocation;

	@Schema(description = "방 유형")
	private RoomType roomType;

	/*
	 * RoomCategory
	 */

	@Schema(description = "카테고리 번호")
	private Long categoryNo;

	@Schema(description = "거래 유형")
	private TransactionType transactionType;

	@Schema(description = "보증금")
	private Long depositPrice;

	@Schema(description = "월세")
	private Long monthlyPrice;


	/*
	 * User
	 */

	@Schema(description = "방 등록한 사용자 아이디")
	private String id;

	public static RoomDto ofResult(RoomResult result) {

		return RoomDto.builder()
			.roomNo(result.getRoomNo())
			.roomTitle(result.getRoomTitle())
			.roomLocation(result.getRoomLocation())
			.roomType(result.getRoomType())
			.categoryNo(result.getCategoryNo())
			.transactionType(result.getTransactionType())
			.depositPrice(result.getDepositPrice())
			.monthlyPrice(result.getMonthlyPrice())
			.id(result.getId())
			.build();
	}

}
