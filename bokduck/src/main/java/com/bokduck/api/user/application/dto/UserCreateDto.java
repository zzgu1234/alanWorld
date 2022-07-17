package com.bokduck.api.user.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Schema(description = "회원가입")
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
public class UserCreateDto {

	@Schema(description = "사용자 아이디")
	private String id;

	@Schema(description = "사용자 이름")
	private String name;

	@Schema(description = "사용자 암호")
	private String password;

	@Schema(description = "사용자 이메일")
	private String email;

	@Schema(description = "사용자 휴대폰")
	private String mobile;

}
