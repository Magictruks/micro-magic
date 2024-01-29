package com.mongo_crud_service.core.configuration;

import com.authentication_jwt.jwt.services.JwtFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtFilterService jwtFilterService;

    private static final String[] AUTH_WHITELIST = {
            "/api/users/auth/checkPassword",
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable);
//                .authorizeHttpRequests(auth ->
//                        auth
//                                .requestMatchers(AUTH_WHITELIST).permitAll()
//                                .anyRequest()
//                                .authenticated()
//                )
//                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Add Jwt Authentication Filter
//        jwtFilterService.addJwtFilter(http);

        return http.build();
    }
}
