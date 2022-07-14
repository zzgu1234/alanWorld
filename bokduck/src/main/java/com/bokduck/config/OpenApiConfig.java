package com.bokduck.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    GroupedOpenApi webApi() {
        return GroupedOpenApi.builder()
                .group("Web Api")
                .pathsToMatch("/api/**")
                .packagesToScan("com.bokduck.ui")
                .build();
    }

	@Bean
	OpenAPI applicationApi() {
		Info info = new Info()
				.title("Bokduck API Documentation")
				.description("부동산 중개플랫폼");

//		SecurityScheme webAuth =
//				new SecurityScheme()
//				.name("JWT Token")
//	            .description("for Web Application API")
//	            .type(Type.HTTP)
//	            .scheme("bearer")
//	            .bearerFormat("JWT");
	    
//	    SecurityRequirement securityRequirement =
//	            new SecurityRequirement()
//	                .addList("webAuth");
	    
	    return new OpenAPI()
//	    		.components(
//	    	            new Components()
//	    	                .addSecuritySchemes("webAuth", webAuth)
//	    	        )
//	    	    .addSecurityItem(securityRequirement)
				.info(info);

	}

}
