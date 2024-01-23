package com.authentication_jwt.jwt.services;

import com.authentication_jwt.jwt.configuration.JwtAuthenticationEntryPoint;
import com.authentication_jwt.jwt.configuration.JwtRequestFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@Slf4j
public class JwtFilterService {
    private final JwtVerifyService jwtVerifyService;
    private final JwtReadService jwtReadService;
    private final JwtAuthenticationEntryPoint unauthorizedHandler;

    public void addJwtFilter(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(new JwtRequestFilter(jwtVerifyService, jwtReadService), UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> exception.authenticationEntryPoint(unauthorizedHandler));

    }
}
