package com.travel.agency.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * Swagger/OpenAPI 설정
 */
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(List.of(
                        new Server()
                                .url("http://localhost:8082")
                                .description("Local Server"),
                        new Server()
                                .url("https://api.example.com")
                                .description("Production Server")
                ))
                .info(new Info()
                        .title("기업출장 여행사 API")
                        .description("기업출장 전문 여행사 B2B 플랫폼 REST API 문서")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("API Support")
                                .email("support@travelagency.com")
                                .url("https://travelagency.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html"))
                );
    }
}
