package com.iot.sensordataapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Sensor Streaming Data API")
                        .description("This API provides real-time streaming and historical data of sensor reading min,max,avg amd median values. ")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("API Support")
                                .url("http://example.com/contact")
                                .email("support@example.com"))
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org")))
                .externalDocs(new ExternalDocumentation()
                        .description("Full Documentation")
                        .url("http://example.com/docs"))
                .components( new Components().addSecuritySchemes(
                        "api",
                        new SecurityScheme()
                                .scheme("bearer")
                                .type(SecurityScheme.Type.HTTP)
                                .bearerFormat("jwt")
                                .name("api")
                ));
    }
}
