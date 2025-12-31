package za.co.uride.userservice.security;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import za.co.uride.userservice.converter.JwtConvertor;
import za.co.uride.userservice.dto.URideUserDetails;

import java.util.Collections;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfig implements WebMvcConfigurer {

    @Value("${spring.security.oauth2.resourceserver.jwt.jwk-set-uri}")
    private String key;
    private final JwtConvertor jwtConvertor;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(securitySessionManagementConfigurer -> securitySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        http.cors(httpSecurityCorsConfigurer -> httpSecurityCorsConfigurer.configurationSource(request -> {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.addAllowedHeader("*");
            corsConfiguration.setAllowedOrigins(Collections.singletonList("http://auth-server:9000"));
            corsConfiguration.setAllowCredentials(true);
            corsConfiguration.addAllowedMethod("*");
            return corsConfiguration;
        }));

        http.authorizeHttpRequests(auth -> auth.requestMatchers("/v3/api-docs", "/v3/api-docs/swagger-config", "/swagger-ui/*",
                        "/user/register/*", "/user/register-staff/*",
                        "/user/register-passenger/*", "/user/verify/*", "/user/complete-register/*",
                        "/otp/verify/", "/user-verification/complete/*", "/user-verification/start/*",
                        "/oauth2/token", "/swagger-ui/oauth2-redirect.html", "/contact/available/*",
                        "/user-service/v3/api-docs", "/actuator/*", "/actuator").anonymous().anyRequest().authenticated())
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(jwtConfigurer -> jwtConfigurer.jwtAuthenticationConverter(source -> {
                    long userId = Long.parseLong(source.getClaim("userId").toString());
                    URideUserDetails.URideUserDetailsBuilder customUserDetailsBuilder = URideUserDetails.builder().authorities(jwtConvertor.convert(source)).username(source.getSubject());
                    JwtAuthenticationToken jwtAuthenticationToken = new JwtAuthenticationToken(jwtConvertor.convert(source), source.getSubject(), userId);
                    jwtAuthenticationToken.setDetails(customUserDetailsBuilder.build());
                    jwtAuthenticationToken.setAuthenticated(true);
                    return jwtAuthenticationToken;
                }))
                );
        http.sessionManagement(session -> session.maximumSessions(1));
        http.csrf(httpSecurityCsrfConfigurer ->
                httpSecurityCsrfConfigurer.ignoringRequestMatchers("/v3/api-docs", "/v3/api-docs/swagger-config",
                        "/swagger-ui/*", "/user/register/*", "/user/register-staff/*", "/user-verification/complete/*",
                        "/user/register-passenger/*", "/user/verify/*", "/otp/verify/", "/user/complete-register/*",
                        "/user-verification/start/*", "/oauth2/token", "/swagger-ui/oauth2-redirect.html", "/contact/available/*", "/user-service/v3/api-docs"));
        return http.build();
    }

    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri(key).build();
    }

}
