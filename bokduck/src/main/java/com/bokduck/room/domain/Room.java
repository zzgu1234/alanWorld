package com.bokduck.room.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bokduck.room.value.RoomType;
import com.bokduck.room.value.TransactionType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(name = "room")
public class Room {

	/*
	 * 번호
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ROOM_NO", nullable = false)
	private Long roomNo;

	/*
	 * 방 유형
	 */
	@Column(name = "ROOM_TYPE", nullable = false)
	private RoomType roomType;

	/*
	 * 거래 유형
	 */
	@Column(name = "TRANSACTION_TYPE", nullable = false)
	private TransactionType transactionType;


	/*
	 * 보증금
	 */
	@Column(name = "DEPOSIT_PRICE", nullable = false)
	private Long depositPrice;


	/*
	 * 보증금
	 */
	@Column(name = "MONTHLY_PRICE", nullable = false)
	private Long monthlyPrice;

}
