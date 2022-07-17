package com.bokduck.api.user.ui.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "로그인 후 응답")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserLoginResponse {

	@Schema(description = "사용자 아이디")
	private String id;

	@Schema(description = "jwt token")
	private String token;

}
