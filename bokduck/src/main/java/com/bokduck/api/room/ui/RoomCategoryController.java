package com.bokduck.api.room.ui;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bokduck.api.room.application.RoomCategoryService;
import com.bokduck.api.room.application.dto.RoomCategoryCreateDto;
import com.bokduck.api.room.application.dto.RoomCategoryUpdateDto;
import com.bokduck.api.room.ui.dto.RoomCategoryCreateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "RoomCategory Controller")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class RoomCategoryController {

	private final RoomCategoryService categoryService;

	@Operation(summary = "거래유형 등록")
	  @ApiResponses({
	      @ApiResponse(responseCode = "201"),
	      @ApiResponse(responseCode = "204"),
	      @ApiResponse(responseCode = "400", description = "요청값 에러"),
	  })
	@PostMapping("/category/{roomNo}")
	public RoomCategoryCreateResponse create(@PathVariable Long roomNo
			, @Valid @RequestBody RoomCategoryCreateDto createDto) throws Exception {

		return categoryService.create(roomNo, createDto);
	}

	@Operation(summary = "거래유형 수정")
	  @ApiResponses({
	      @ApiResponse(responseCode = "200"),
	      @ApiResponse(responseCode = "403", description = "수정 권한 없음"),
	      @ApiResponse(responseCode = "404", description = "수정하려는 거래유형 없음"),
	  })
	@PatchMapping("/category")
	public void edit(@Valid @RequestBody RoomCategoryUpdateDto editDto) throws Exception{

		categoryService.update(editDto);
	}

	@Operation(summary = "거래유형 삭제")
	  @ApiResponses({
	      @ApiResponse(responseCode = "200"),
	      @ApiResponse(responseCode = "404", description = "삭제하려는 거래유형 없음"),
	  })
	@DeleteMapping("/category/{categoryNo}")
	public void delete(@PathVariable Long categoryNo) throws Exception{

		categoryService.delete(categoryNo);
	}

}
