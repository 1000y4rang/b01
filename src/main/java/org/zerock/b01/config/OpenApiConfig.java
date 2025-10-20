package org.zerock.b01.config;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Boot 01 Project API")
                        .description("Spring Boot 3 + OpenAPI (springdoc) 설정 예시")
                        .version("v1.0.0"));
    }
}