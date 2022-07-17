package com.bokduck.api.user.application.dto;

import com.bokduck.api.user.domain.User;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "회원가입")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
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

	public User toUser() {
		return User.builder()
				.id(id)
				.password(password)
				.email(email)
				.mobile(mobile)
				.build();
	}

}
