package za.co.uride.userservice.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.Scopes;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Authentication"))
                .components(new Components())
                .info(new Info().title("User management mirco-service Rest APIs")
                        .description("API Services")
                        .version("1.0.0").contact(new Contact().name("Mpendulo Malangwane").email("mpendulmalangwane@gmail.com"))
                        .license(new License().name("© Krypton-byte")
                                .url("https://www.kryptonbtye.co.za")));
    }


}
