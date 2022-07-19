package com.bokduck.api.room.application.dto;

import com.bokduck.api.room.domain.Room;
import com.bokduck.api.room.value.RoomType;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "방 수정")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoomUpdateDto {

	@Schema(description = "방 번호")
	private Long roomNo;

	@Schema(description = "방 제목")
	private String roomTitle;

	@Schema(description = "방 위치")
	private String roomLocation;

	@Schema(description = "방 유형")
	private RoomType roomType;

	public static RoomUpdateDto of(Room room) {
		return RoomUpdateDto.builder()
				.roomTitle(room.getRoomTitle())
				.roomLocation(room.getRoomLocation())
				.roomType(room.getRoomType())
				.build();
	}

}
