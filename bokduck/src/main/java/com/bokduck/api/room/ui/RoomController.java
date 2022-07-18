package com.bokduck.api.room.ui;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bokduck.component.JwtManager;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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

	@Autowired
	JwtManager jwt;

	@Operation(summary = "방 목록")
	  @ApiResponses({
		  @ApiResponse(responseCode = "200"),
	      @ApiResponse(responseCode = "401", description = "검색조건 오류", content = @Content),
	  })
	@GetMapping("/rooms")
	public String findAll() throws Exception{

		return "!";
	}

	@Operation(summary = "방 상세")
	  @ApiResponses({
	      @ApiResponse(responseCode = "200"),
	      @ApiResponse(responseCode = "404", description = "방 검색 실패", content = @Content),
	  })
	@GetMapping("/room")
	public void find() throws Exception{

	}

	@Operation(summary = "방 등록")
	  @ApiResponses({
	      @ApiResponse(responseCode = "200"),
	  })
	@PostMapping("/room")
	public void create() throws Exception{


	}

	@Operation(summary = "방 수정")
	  @ApiResponses({
	      @ApiResponse(responseCode = "200"),
	      @ApiResponse(responseCode = "404", description = "수정하려는 방 없음", content = @Content),
	  })
	@PatchMapping("/room")
	public void edit() throws Exception{


	}

	@Operation(summary = "방 삭제")
	  @ApiResponses({
	      @ApiResponse(responseCode = "200"),
	      @ApiResponse(responseCode = "404", description = "삭제하려는 방 없음", content = @Content),
	  })
	@DeleteMapping("/room")
	public void delete() throws Exception{


	}

}
