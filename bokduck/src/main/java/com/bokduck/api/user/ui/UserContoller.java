package com.bokduck.api.user.ui;

import javax.validation.Valid;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bokduck.api.user.application.UserService;
import com.bokduck.api.user.application.dto.UserCreateDto;
import com.bokduck.api.user.application.dto.UserLoginDto;
import com.bokduck.api.user.ui.dto.UserCreateResponse;
import com.bokduck.api.user.ui.dto.UserLoginResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "User Controller")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class UserContoller {

	private final UserService userService;

	@Operation(summary = "회원 가입")
	  @ApiResponses({
	      @ApiResponse(responseCode = "201"),
	      @ApiResponse(responseCode = "204"),
	      @ApiResponse(responseCode = "400", description = "요청값 에러"),
	      @ApiResponse(responseCode = "409", description = "아이디 중복"),
	  })
	@PostMapping("/regist")
	public UserCreateResponse regist(@Valid @RequestBody UserCreateDto createDto) throws Exception {

		return userService.regist(createDto);
	}

	@Operation(summary = "로그인")
	  @ApiResponses({
	      @ApiResponse(responseCode = "200"),
	      @ApiResponse(responseCode = "204"),
	      @ApiResponse(responseCode = "400", description = "요청값 에러"),
	      @ApiResponse(responseCode = "404", description = "아이디 정보 없음"),
	  })
	@PostMapping("/login")
	public UserLoginResponse login(@RequestBody UserLoginDto loginDto) throws Exception {

		return userService.login(loginDto);
	}

}
