package com.backend.template.base.swagger;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    //http://localhost:8080/swagger-ui/index.html 로 접속

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("springdoc-openapi")
                        .version("1.0")
                        .description("template swagger 실행"));
    }
}