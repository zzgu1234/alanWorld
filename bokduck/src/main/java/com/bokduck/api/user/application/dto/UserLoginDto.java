package com.bokduck.api.user.application.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginDto {

	@Schema(description = "사용자 아이디")
	private String id;

	@Schema(description = "사용자 비밀번호")
	private String password;
}
