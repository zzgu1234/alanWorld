package com.bokduck.api.room.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bokduck.api.room.value.TransactionType;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(schema = "bokduck", name = "roomcategory")
public class RoomCategory {

	/*
	 * 카테고리 번호
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CATEGORY_NO", nullable = false)
	private Long categoryNo;

	/*
	 * 거래 유형
	 */
	@Column(name = "TRANSACTION_TYPE", nullable = false)
	@Enumerated(EnumType.STRING)
	private TransactionType transactionType;


	/*
	 * 보증금
	 */
	@Column(name = "DEPOSIT_PRICE", nullable = false)
	private Long depositPrice;


	/*
	 * 월세
	 */
	@Column(name = "MONTHLY_PRICE")
	private Long monthlyPrice;

	/*
	 * 방 번호
	 */
	@Column(name = "ROOM_NO")
	private Long roomNo;

}
