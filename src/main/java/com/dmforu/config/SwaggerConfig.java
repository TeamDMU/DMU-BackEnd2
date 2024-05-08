package com.dmforu.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {

        Info info = new Info().title("DMforU API 명세서").version("v1")
                .description("API 명세서")
                .license(new License().name("MIT License").url("https://github.com/TeamDMU/DMU-BackEnd2/blob/main/LICENSE"));

        return new OpenAPI()
                .components(new Components())
                .info(info);
    }
}