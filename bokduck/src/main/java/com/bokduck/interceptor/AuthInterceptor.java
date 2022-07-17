package com.bokduck.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.bokduck.component.JwtManager;

@Component
// 인터셉터 설정
public class AuthInterceptor implements HandlerInterceptor {

	@Autowired
	JwtManager jwt;

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response,
			Object handler) throws Exception {

		if(response.getStatus() != 200) {
			return true;
		}

		String token = request.getHeader("Authorization");

        if (token != null && token.startsWith("Bearer ")) {
        	token = token.substring("Bearer ".length());
        } else {

        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
        }

        if(jwt.validateJwt(token) != 0) {
        	response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
        }

        String id = jwt.getId(token);

        request.setAttribute("accessToken", token);
        request.setAttribute("accessTokenId", id);

		return true;
	}

}
