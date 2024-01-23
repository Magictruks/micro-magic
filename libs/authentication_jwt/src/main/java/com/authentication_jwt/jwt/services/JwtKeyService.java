package com.authentication_jwt.jwt.services;

import com.authentication_jwt.jwt.configuration.JwtConfig;
import com.authentication_jwt.jwt.configuration.JwtEnvironmentServiceConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.interfaces.RSAPublicKey;
import java.util.Base64;

@RequiredArgsConstructor
@Service
@Slf4j
public class JwtKeyService {
    private final JwtConfig jwtConfig;
    private final JwtEnvironmentServiceConfig serviceConfig;
    private final LoadRSAKeys loadRSAKeys;

    public void setPublicKey(String publicKeyStr) {
        jwtConfig.setPublicKey(publicKeyStr);
    }
    public void setPublicKey(byte[] publicKeyBytes) {
        String publicKey = Base64.getEncoder().encodeToString(publicKeyBytes);
        jwtConfig.setPublicKey(publicKey);
    }

    public RSAPublicKey getPublicKey() throws GeneralSecurityException, IOException {
        if (Strings.isNotEmpty(jwtConfig.getPublicKey())) {
            return LoadRSAKeys.loadPublicKeyFromPem(jwtConfig.getPublicKey());
        }

        if (Strings.isNotEmpty(jwtConfig.getPublicKeyPath())) {
            return loadRSAKeys.loadPublicKeyFromResourceFile(jwtConfig.getPublicKeyPath());
        }

        return getPublicKeyFormAuthService();
    }

    public String getPublicKeyString() throws IOException {
        if (Strings.isEmpty(jwtConfig.getPublicKeyPath())) {
            log.warn("You need to set Public key path in your config");
            return null;
        }
        return loadRSAKeys.getPublicKeyStringFromResourcePath(jwtConfig.getPublicKeyPath());
    }

    private RSAPublicKey getPublicKeyFormAuthService() {
        if (Strings.isEmpty(serviceConfig.getAuthUrl())) {
            log.warn("Auth Url is not set");
            return null;
        }

        String url = serviceConfig.getAuthUrl() + "/api/auth/public-key";
        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.exchange(
                    uriComponentsBuilder.encode().toUriString(),
                    HttpMethod.GET,
                    null,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK && response.getBody() != null) {
                String publicKeyStr = response.getBody();
                setPublicKey(publicKeyStr);
                return LoadRSAKeys.loadPublicKeyFromPem(publicKeyStr);
            }
        } catch (Exception e) {
            log.error("Error", e);
        }
        return null;
    }

}
