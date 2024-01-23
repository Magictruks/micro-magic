package com.authentication_jwt.jwt.configuration;

import com.authentication_jwt.jwt.models.JwtUser;
import com.authentication_jwt.jwt.services.JwtReadService;
import com.authentication_jwt.jwt.services.JwtVerifyService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;

@RequiredArgsConstructor
@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private final JwtVerifyService jwtVerifyService;
    private final JwtReadService jwtReadService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        final String requestTokenHeader = request.getHeader("Authorization");
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            logger.warn("JWT Token does not begin with Bearer String");
            chain.doFilter(request, response);
            return;
        }

        String jwtToken = requestTokenHeader.substring(7);
        if (!jwtVerifyService.isTokenValid(jwtToken)) {
            logger.warn("Unauthorized");
            chain.doFilter(request, response);
            return;
        }

        JwtUser jwtUser = null;
        try {
            jwtUser = jwtReadService.extractData(jwtToken);
        } catch (GeneralSecurityException e) {
            throw new IllegalArgumentException(e);
        }
        UserDetails userDetails = getUserDetails(jwtUser);

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);

        chain.doFilter(request, response);
    }

    private UserDetails getUserDetails(JwtUser jwtUser) {
        var username = jwtUser.getEmail() != null ? jwtUser.getEmail() : jwtUser.getUserName();
        return new User(username, jwtUser.getId(), new ArrayList<>());
    }
}

