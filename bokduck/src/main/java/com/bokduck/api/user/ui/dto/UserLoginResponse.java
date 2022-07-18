package com.bokduck.api.user.ui.dto;

import com.bokduck.common.HttpStatusResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Schema(description = "로그인 후 응답")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse extends HttpStatusResponse{

	@Schema(description = "사용자 아이디")
	private String id;

	@Schema(description = "jwt token")
	private String token;

	UserLoginResponse of(HttpStatusResponse statusResponse) {
		return UserLoginResponse.builder()
				.id(id)
				.token(token)
				.build();
	}
}
