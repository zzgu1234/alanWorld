package com.bokduck.common;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Schema(description = "HttpStatus 응답")
@Getter
@SuperBuilder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class HttpStatusResponse {

	@Schema(description = "HttpStatus 코드")
	protected int code;

	@Schema(description = "메시지")
	protected String msg;

}
