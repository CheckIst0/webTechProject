package web.tech.project.api.core.configs;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;

public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info().title("Resto API")
                        .description("App for restaurant")
                        .version("1.0.0")
                        .license(new License().name("License").url("http://unlicense.org")));
    }
}
