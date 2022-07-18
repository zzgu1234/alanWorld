package com.bokduck.api.room.infra;

import com.bokduck.api.room.value.RoomType;
import com.bokduck.api.room.value.TransactionType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RoomResult {

	/*
	 * Room
	 */
	private Long roomNo;
	private String roomTitle;
	private String roomLocation;
	private RoomType roomType;

	/*
	 * RoomCategory
	 */
	private Long categoryNo;
	private TransactionType transactionType;
	private Long depositPrice;
	private Long monthlyPrice;

	/*
	 * User
	 */
	private String id;

}
