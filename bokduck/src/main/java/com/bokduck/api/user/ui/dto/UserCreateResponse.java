package com.bokduck.api.user.ui.dto;

import com.bokduck.common.HttpStatusResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Schema(description = "회원가입 후 응답")
@Getter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponse extends HttpStatusResponse {

	@Schema(description = "사용자 아이디")
	private String id;

}
