package com.bokduck.api.room.application;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bokduck.api.room.application.dto.RoomCategoryCreateDto;
import com.bokduck.api.room.application.dto.RoomCategoryUpdateDto;
import com.bokduck.api.room.domain.Room;
import com.bokduck.api.room.domain.RoomCategory;
import com.bokduck.api.room.infra.RoomCategoryRepository;
import com.bokduck.api.room.infra.RoomRepository;
import com.bokduck.api.room.ui.dto.RoomCategoryCreateResponse;
import com.bokduck.component.JwtManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoomCategoryService {

	@Autowired
	JwtManager jwt;

	private final RoomCategoryRepository categoryRepository;
	private final RoomRepository roomRepository;
	private final HttpServletRequest request;

	// 거래유형 등록
	@Transactional
	public RoomCategoryCreateResponse create(Long roomNo, RoomCategoryCreateDto createDto) throws Exception {

		// 아이디 정보를 가져옴
		String id = jwt.getId(request);
		Room room = roomRepository.findById(roomNo)
				.orElseThrow(() -> new RuntimeException("Room info not found."));

		if( !id.equals(room.getId()) ) {
			throw new RuntimeException("No permission!");
		}

		RoomCategory category = RoomCategory.builder()
				.roomNo(roomNo)
				.transactionType(createDto.getTransactionType())
				.depositPrice(createDto.getDepositPrice())
				.monthlyPrice(createDto.getMonthlyPrice())
				.build();

		// request값이 비어있을경우
		if( createDto.getTransactionType() == null
				|| createDto.getDepositPrice() == null ) {

			return RoomCategoryCreateResponse.builder()
					.code(HttpStatus.BAD_REQUEST.value())
					.msg("No Request Information.")
					.build();
		}

		categoryRepository.save(category);

		return RoomCategoryCreateResponse.builder()
				.roomNo(roomNo)
				.transactionType(createDto.getTransactionType())
				.code(HttpStatus.CREATED.value())
				.msg("Success")
				.build();
	}

	public void update(RoomCategoryUpdateDto editDto) throws Exception {

		String id = jwt.getId(request);
		Room room = roomRepository.findById(editDto.getRoomNo())
				.orElseThrow(() -> new RuntimeException("Room info not found."));

		if( !id.equals(room.getId()) ) {
			throw new RuntimeException("No permission");
		}

		RoomCategory category = categoryRepository.findById(editDto.getCategoryNo())
				.orElseThrow(() -> new RuntimeException("Category info not found."));

		category.update(editDto);
		categoryRepository.save(category);

	}

	public void delete(Long categoryNo) throws Exception {

		String id = jwt.getId(request);

		RoomCategory category = categoryRepository.findById(categoryNo)
				.orElseThrow(() -> new RuntimeException("Category info not found."));

		Room room = roomRepository.findById(category.getRoomNo())
				.orElseThrow(() -> new RuntimeException("Room info not found."));

		if( !id.equals(room.getId()) ) {
			throw new RuntimeException("No permission!");
		}

		category.delete(categoryNo);
		categoryRepository.save(category);
	}
}
