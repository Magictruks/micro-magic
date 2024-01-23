package com.authentication_jwt.jwt.services;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;

@RequiredArgsConstructor
@Service
@Slf4j
public class JwtVerifyService {

    private final JwtKeyService jwtKeyService;

    public boolean isTokenValid(String token) {
        try {
            buildJWTVerifier().verify(token.replace("Bearer ", ""));
            // if token is valid no exception will be thrown
            return true;
        } catch (CertificateException e) {
            //if CertificateException comes from buildJWTVerifier()
            log.info("Certificate exception");
            return false;
        } catch (JWTVerificationException e) {
            // if JWT Token in invalid
            log.info("Invalid token");
            return false;
        } catch (Exception e) {
            // If any other exception comes
            log.error("Error", e);
            return false;
        }
    }

    private JWTVerifier buildJWTVerifier() throws GeneralSecurityException, IOException {
        var algo = Algorithm.RSA256(jwtKeyService.getPublicKey(), null);
        return JWT.require(algo).build();
    }
}
