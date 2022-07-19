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

import com.bokduck.api.room.application.dto.RoomCategoryUpdateDto;
import com.bokduck.api.room.value.TransactionType;
import com.bokduck.api.room.value.UseYn;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
@Table(schema = "bokduck", name = "roomcategory")
public class RoomCategory {

	/*
	 * 거래유형 번호
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

	/*
	 * 거래유형 삭제 여부
	 */
	@Column(name = "use_yn", nullable = false)
	@Enumerated(EnumType.STRING)
	private UseYn useYn;

	@Builder
	public RoomCategory(@NotNull Long categoryNo,
			@NotNull TransactionType transactionType,
			@NotNull Long depositPrice,
			Long monthlyPrice,
			@NotNull Long roomNo,
			@NotNull UseYn useYn) {
		super();
		this.categoryNo = categoryNo;
		this.transactionType = transactionType;
		this.depositPrice = depositPrice;
		this.monthlyPrice = monthlyPrice;
		this.roomNo = roomNo;
		if(useYn == null) useYn = UseYn.Y;
		this.useYn = useYn;
	}

	public void update(RoomCategoryUpdateDto editDto) {
		this.transactionType = editDto.getTransactionType();
		this.depositPrice = editDto.getDepositPrice();
		this.monthlyPrice = editDto.getMonthlyPrice();
		this.roomNo = editDto.getRoomNo();
	}

	public void delete(Long roomNo){
		this.useYn = UseYn.N;
	}

}
