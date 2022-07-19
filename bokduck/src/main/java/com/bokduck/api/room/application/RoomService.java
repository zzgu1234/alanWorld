package com.bokduck.api.room.application;

import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bokduck.api.room.application.dto.RoomCreateDto;
import com.bokduck.api.room.application.dto.RoomDto;
import com.bokduck.api.room.application.dto.RoomListRequest;
import com.bokduck.api.room.application.dto.RoomUpdateDto;
import com.bokduck.api.room.domain.Room;
import com.bokduck.api.room.infra.RoomMapper;
import com.bokduck.api.room.infra.RoomRepository;
import com.bokduck.api.room.infra.dto.RoomListParam;
import com.bokduck.api.room.ui.dto.RoomCreateResponse;
import com.bokduck.api.room.value.UseYn;
import com.bokduck.common.PageResult;
import com.bokduck.component.JwtManager;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class RoomService {

	@Autowired
	JwtManager jwt;

	private final RoomRepository roomRepository;
	private final RoomMapper roomMapper;
	private final HttpServletRequest request;

	// 방 등록
	@Transactional
	public RoomCreateResponse create(RoomCreateDto createDto)throws Exception {

		// 아이디 정보를 가져옴
		String id = jwt.getId(request);
		Room room = Room.builder()
				.roomTitle(createDto.getRoomTitle())
				.roomLocation(createDto.getRoomLocation())
				.roomType(createDto.getRoomType())
				.id(id)
				.useYn(UseYn.Y)
				.build();

		// request값이 비어있을경우
		if( createDto.getRoomTitle() == null
				|| createDto.getRoomLocation() == null
				|| createDto.getRoomType() == null
				|| id == null
				) {

			return RoomCreateResponse.builder()
					.code(HttpStatus.BAD_REQUEST.value())
					.msg("No Request Information.")
					.build();
		}

		roomRepository.save(room);

		return RoomCreateResponse.builder()
				.id(id)
				.code(HttpStatus.CREATED.value())
				.msg("Success")
				.build();
	}

	public PageResult<RoomDto> findAll(RoomListRequest listRequest) throws Exception {

		// 내글보기 체크
		String id = null;
		if( "Y".equals(listRequest.getMyRoom()) ) {
			id = jwt.getId(request);
		}

		RoomListParam param = RoomListParam.builder()
				.roomType(listRequest.getRoomType())
				.transactionType(listRequest.getTransactionType())
				.priceType(listRequest.getPriceType())
				.min(listRequest.getMin())
				.max(listRequest.getMax())
				.id(id)
				.build();

		return PageResult.<RoomDto>builder()
				.items(roomMapper.findAll(param)
					.stream()
					.map(RoomDto::ofResult)
					.collect(Collectors.toList()) )
				.build();
	}

	public Optional<RoomDto> findDetail(Long roomNo) {

		RoomDto roomInfo = roomMapper.findDetail(roomNo)
				.map(RoomDto::ofResult)
				.orElse(null);

		if( roomInfo == null ) {
			throw new RuntimeException("Room info not found.");
		}

		return Optional.of(roomInfo);
	}

	@Transactional
	public void update(RoomUpdateDto editDto) throws Exception {

		String id = jwt.getId(request);
		Room room = roomRepository.findById(editDto.getRoomNo())
				.orElseThrow(() -> new RuntimeException("Room info not found."));

		if( !id.equals(room.getId()) ) {
			throw new RuntimeException("No permission");
		}

		room.update(editDto);
		roomRepository.save(room);
	}

	@Transactional
	public void delete(Long roomNo) throws Exception {

		String id = jwt.getId(request);
		Room room = roomRepository.findById(roomNo)
				.orElseThrow(() -> new RuntimeException("Room info not found."));

		if( !id.equals(room.getId()) ) {
			throw new RuntimeException("No permission");
		}

		room.delete(roomNo);

		roomRepository.save(room);
	}

}
