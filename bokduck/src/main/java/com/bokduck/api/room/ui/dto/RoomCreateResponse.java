package com.bokduck.api.room.ui.dto;

import com.bokduck.common.HttpStatusResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Schema(description = "방 생성 후 응답")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class RoomCreateResponse extends HttpStatusResponse {

	@Schema(description = "방 등록한 사용자 아이디")
	private String id;
}
