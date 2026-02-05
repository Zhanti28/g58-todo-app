package se.lexicon.g58todoapp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    OpenAPI apiInfo() {

        return new OpenAPI()
                .info(new Info()
                        .title("G58 Todo App API")
                        .description("API documentation for G58 Todo App")
                        .version("1.0.0")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("http://springdoc.org"))
                );
    }
}
