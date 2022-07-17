package com.bokduck.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.bokduck.interceptor.AuthInterceptor;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer{

	@Autowired
	AuthInterceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {

		// auth정보가 없을경우 SC_UNAUTHORIZED
		registry.addInterceptor(interceptor)

		.addPathPatterns("/api/**")

		//회원가입 / 로그인 / swagger 경로는 제외한다.
		.excludePathPatterns("/api/regist")
		.excludePathPatterns("/api/login")
		.excludePathPatterns("/swagger-ui/**");

	}

}
