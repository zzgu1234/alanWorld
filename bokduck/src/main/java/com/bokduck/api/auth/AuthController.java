package com.bokduck.api.auth;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;

@Tag(name = "Auth Controller")
@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class AuthController {


	@GetMapping("/login")
	public String login() {

		return "!";
	}

	@GetMapping("/logout")
	public void logout() {

	}
}
