package com.authentication_jwt.jwt.services;

import com.authentication_jwt.jwt.mappers.JwtMapper;
import com.authentication_jwt.jwt.models.JwtUser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RequiredArgsConstructor
@Service
@Slf4j
public class JwtReadService {
    private final JwtKeyService jwtKeyService;
    private final JwtMapper jwtMapper;
    public JwtUser extractData(String token) throws GeneralSecurityException, IOException {
        Jwt parse = Jwts.parser()
                .setSigningKey(jwtKeyService.getPublicKey())
                .parse(token);

        JwtUser user = new JwtUser();
        try {
            user = new ObjectMapper()
                    .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                    .convertValue(parse.getBody(), JwtUser.class);
        } catch (Exception e) {
            log.error("Error parsing", e);
        }

        return new JwtUser()
                .setId(parse.getBody().toString())
                .setUserName(user.getUserName())
                .setEmail(user.getEmail());
    }
}
