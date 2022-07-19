package com.bokduck.api.room.ui;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bokduck.api.room.application.RoomService;
import com.bokduck.api.room.application.dto.RoomCreateDto;
import com.bokduck.api.room.application.dto.RoomDto;
import com.bokduck.api.room.application.dto.RoomListRequest;
import com.bokduck.api.room.application.dto.RoomUpdateDto;
import com.bokduck.api.room.ui.dto.RoomCreateResponse;
import com.bokduck.common.PageResult;
import com.bokduck.component.JwtManager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Room Controller")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RoomController {

	private final RoomService roomService;

	@Autowired
	JwtManager jwt;

	@Operation(summary = "방 목록")
	  @ApiResponses({
		  @ApiResponse(responseCode = "200"),
	      @ApiResponse(responseCode = "401", description = "검색조건 오류"),
	  })
	@GetMapping("/rooms")
	public PageResult<RoomDto> findAll(
	        @Parameter(description = "검색조건 - 방 유형(ON : 원룸, TW : 투룸, TH : 쓰리룸")
	        @RequestParam(required = false) String roomType,
	        @Parameter(description = "검색조건 - 거래 유형(C : 전세, M : 월세")
	        @RequestParam(required = false) String transactionType,
	        @Parameter(description = "검색조건 - 금액 유형(D : 보증금, M : 월세")
	        @RequestParam(required = false) String priceType,
	        @Parameter(description = "검색조건 - 최소 가격범위")
	        @RequestParam(required = false) Long min,
	        @Parameter(description = "검색조건 - 최대 가격범위")
	        @RequestParam(required = false) Long max,
	        @Parameter(description = "내글 여부")
	        @RequestParam(defaultValue = "Y") @NotNull String myRoom
			) throws Exception{

		RoomListRequest request = RoomListRequest.builder()
				.roomType(roomType)
				.transactionType(transactionType)
				.priceType(priceType)
				.min(min)
				.max(max)
				.myRoom(myRoom)
				.build();

		return roomService.findAll(request);
	}

	@Operation(summary = "방 상세")
	  @ApiResponses({
	      @ApiResponse(responseCode = "200"),
	      @ApiResponse(responseCode = "404", description = "방 조회 실패"),
	  })
	@GetMapping("/room/{roomNo}")
	public RoomDto find(@PathVariable Long roomNo) throws Exception{

		return roomService.findDetail(roomNo)
				.orElseThrow(() -> new RuntimeException());
	}

	@Operation(summary = "방 등록")
	  @ApiResponses({
	      @ApiResponse(responseCode = "201"),
	      @ApiResponse(responseCode = "204"),
	      @ApiResponse(responseCode = "400", description = "요청값 에러"),
	  })
	@PostMapping("/room")
	public RoomCreateResponse create(@Valid @RequestBody RoomCreateDto createDto) throws Exception {

		return roomService.create(createDto);
	}

	@Operation(summary = "방 수정")
	  @ApiResponses({
	      @ApiResponse(responseCode = "200"),
	      @ApiResponse(responseCode = "403", description = "수정 권한 없음"),
	      @ApiResponse(responseCode = "404", description = "수정하려는 방 없음"),
	  })
	@PatchMapping("/room")
	public void edit(@Valid @RequestBody RoomUpdateDto editDto) throws Exception{

		roomService.update(editDto);
	}

	@Operation(summary = "방 삭제")
	  @ApiResponses({
	      @ApiResponse(responseCode = "200"),
	      @ApiResponse(responseCode = "404", description = "삭제하려는 방 없음"),
	  })
	@DeleteMapping("/room/{roomNo}")
	public void delete(@PathVariable Long roomNo) throws Exception{

		roomService.delete(roomNo);
	}

}
