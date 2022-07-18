package com.bokduck.api.room.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.bokduck.api.room.value.RoomType;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(schema = "bokduck", name = "room")
public class Room {

	/*
	 * 방 번호
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROOM_NO", nullable = false)
	private Long roomNo;

	/*
	 * 방 제목
	 */
	@Column(name = "ROOM_TITLE", nullable = false)
	private String roomTitle;

	/*
	 * 방 위치
	 */
	@Column(name = "ROOM_LOCATION", nullable = false)
	private String roomLocation;

	/*
	 * 방 유형
	 */
	@Column(name = "ROOM_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private RoomType roomType;

	/*
	 * 방 등록한 사용자 아이디
	 */
	@Column(name = "id", nullable = false)
	private String id;

//	/*
//	 * 카테고리 번호
//	 */
//	@Column(name = "CATEGORY_NO", nullable = false)
//	private Long categoryNo;

	@Builder
	public Room(@NotNull Long roomNo,
			@NotNull String roomTitle,
			@NotNull String roomLocation,
			@NotNull RoomType roomType,
			@NotNull String id) {

		this.roomNo = roomNo;
		this.roomTitle = roomTitle;
		this.roomLocation = roomLocation;
		this.roomType = roomType;
		this.id = id;
	}

}
