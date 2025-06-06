package com.gef_biotag.Biotag.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityScheme.In;
import io.swagger.v3.oas.models.security.SecurityScheme.Type;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
            .info(new Info().title("API").version("1.0"))
            .addSecurityItem(new SecurityRequirement().addList("Bearer"))
            .components(new io.swagger.v3.oas.models.Components()
                .addSecuritySchemes("Bearer",
                    new SecurityScheme()
                        .type(Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT")
                        .in(In.HEADER)
                        .name("Authorization")));
    }
}
