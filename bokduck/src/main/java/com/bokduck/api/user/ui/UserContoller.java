package com.bokduck.api.user.ui;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bokduck.api.user.application.UserService;
import com.bokduck.api.user.application.dto.UserCreateDto;
import com.bokduck.api.user.ui.dto.UserCreateResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "User Controller")
@Validated
@RestController
@RequestMapping("/api")
public class UserContoller {

	@Autowired
	private UserService userService;

	@Operation(summary = "회원 가입")
	  @ApiResponses({
	      @ApiResponse(responseCode = "200"),
	  })
	@PostMapping("/user")
	public UserCreateResponse create(@Valid @RequestBody UserCreateDto createDto) throws Exception {

		Long userNo = userService.create(createDto);

		return UserCreateResponse.
				.id(id)
				.build();
	}

}
