package com.authentication_jwt.jwt.services;

import com.authentication_jwt.jwt.configuration.JwtConfig;
import com.authentication_jwt.jwt.models.JwtUser;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class JwtGenerateService {
    private final LoadRSAKeys loadRSAKeys;
    private final JwtConfig jwtConfig;
    public  String generateToken(JwtUser userDomain) throws GeneralSecurityException, IOException {
        return Jwts.builder()
                .setSubject(new ObjectMapper().writeValueAsString(userDomain))
                .setClaims(new ObjectMapper().convertValue(userDomain, Map.class))
                .setExpiration(new Date(System.currentTimeMillis() + jwtConfig.getExpirationTime()))
                .signWith(SignatureAlgorithm.RS256, loadRSAKeys.loadPrivateKeyFromResourceFile(jwtConfig.getPrivateKeyPath()))
                .compact();
    }

    public String generateAuthToken() throws IOException, GeneralSecurityException {
        JwtUser userDomain = new JwtUser()
                .setUserName("Admin")
                .setEmail("admin@admin.com");
        return Jwts.builder()
                .setSubject(new ObjectMapper().writeValueAsString(userDomain))
                .setClaims(new ObjectMapper().convertValue(userDomain, Map.class))
                .setExpiration(new Date(System.currentTimeMillis() + 10000)) // 10 sec
                .signWith(SignatureAlgorithm.RS256, loadRSAKeys.loadPrivateKeyFromResourceFile(jwtConfig.getPrivateKeyPath()))
                .compact();
    }
}

