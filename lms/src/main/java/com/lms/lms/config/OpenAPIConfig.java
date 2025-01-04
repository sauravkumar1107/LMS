package com.lms.lms.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .servers(Arrays.asList(
                        new Server().url("http://production-url").description("Production"),
                        new Server().url("http://staging-url").description("Staging"),
                        new Server().url("http://localhost:8090").description("Local")
                ))
                .info(new Info()
                        .title("Lead Management System API")
                        .version("1.0")
                        .description("API documentation for Udaan Lead Management System")
                        .contact(new Contact()
                                .name("Your Name")
                                .email("your.email@example.com")));
    }
}
