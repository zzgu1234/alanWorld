package com.bokduck.api.room.application.dto;

import com.bokduck.api.room.value.RoomType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "방 등록")
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class RoomCreateDto {

	@Schema(description = "방 제목")
	private String roomTitle;

	@Schema(description = "방 위치")
	private String roomLocation;

	@Schema(description = "방 유형")
	private RoomType roomType;

}
