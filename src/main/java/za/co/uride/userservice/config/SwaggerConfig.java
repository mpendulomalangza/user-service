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

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().addSecurityItem(new SecurityRequirement().addList("Authentication"))
                .components(new Components().addSecuritySchemes("Authentication", createCustomApiKeyScheme()))
                .info(new Info().title("User management mirco-service Rest APIs")
                        .description("API Services")
                        .version("1.0.0").contact(new Contact().name("Mpendulo Malangwane").email("mpendulmalangwane@gmail.com"))
                        .license(new License().name("© Krypton-byte")
                                .url("https://www.kryptonbtye.co.za")))
                .components(new Components()
                        .addSecuritySchemes("oauth2_security_scheme",
                                new SecurityScheme()
                                        .type(SecurityScheme.Type.OAUTH2)
                                        .flows(new OAuthFlows()
                                                .authorizationCode(createAuthorizationCodeFlow()
                                                        .authorizationUrl("http://auth-server:9000/oauth2/authorize")// Your auth server's authorize endpoint
                                                        .tokenUrl("http://auth-server:9000/oauth2/token")
                                                        .scopes(new Scopes().addString("find-user-avatar", "find avatar").addString("edit-user-avatar","edit user avatar"))))));
    }

    private SecurityScheme createCustomApiKeyScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.APIKEY)
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");
    }

    private OAuthFlow createAuthorizationCodeFlow() {
        return new OAuthFlow().refreshUrl("http://localhost:1900/user-service/swagger-ui/oauth2-redirect.html")
                .authorizationUrl("http://auth-server:9000/oauth2/authorize");
    }
}
