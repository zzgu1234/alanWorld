package com.bokduck.api.room.ui.dto;

import com.bokduck.api.room.value.TransactionType;
import com.bokduck.common.HttpStatusResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Schema(description = "거래유형 생성 후 응답")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoomCategoryCreateResponse extends HttpStatusResponse {

	@Schema(description = "방 번호")
	private Long roomNo;

	@Schema(description = "거래 유형")
	private TransactionType transactionType;
}
