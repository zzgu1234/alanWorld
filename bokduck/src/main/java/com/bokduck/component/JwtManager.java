package com.bokduck.component;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;

@Component
public class JwtManager extends JwtUtil {

	public String getId (HttpServletRequest request) throws Exception{
		String accessToken = (String)request.getAttribute("accessToken");
		return getId(accessToken);
	}

	public String getId(String token) throws Exception {
		Claims claims = getTokenData(token);
		return (String)claims.get("id");
	}
}
