package com.bokduck.api.user.ui.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Schema(description = "회원가입 후 응답")
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserCreateResponse {

	@Schema(description = "사용자 고유번호")
	private Long userNo;

}
