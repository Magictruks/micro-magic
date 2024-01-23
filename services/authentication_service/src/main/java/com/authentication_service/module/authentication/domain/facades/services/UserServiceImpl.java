package com.authentication_service.module.authentication.domain.facades.services;

import com.authentication_service.core.configuration.EnvironmentServiceConfig;
import com.authentication_service.module.authentication.domain.facades.interfaces.UserService;
import com.authentication_service.module.authentication.domain.facades.models.UserDomainDto;
import com.authentication_service.module.authentication.domain.facades.models.UserRegisterDto;
import com.authentication_service.module.authentication.domain.models.TokenDomain;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    private final EnvironmentServiceConfig serviceConfig;

    @Override
    public Optional<UserDomainDto[]> findAll(Map<String, String> query, TokenDomain token) {
        String url = serviceConfig.getUserUrl() + "/api/users";

        UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.fromHttpUrl(url);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization" , "Bearer " + token.getAccessToken());
        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        query.forEach(uriComponentsBuilder::queryParam);

        ResponseEntity<UserDomainDto[]> response;
        try {
            response = restTemplate.exchange(
                    uriComponentsBuilder.encode().toUriString(),
                    HttpMethod.GET,
                    entity,
                    UserDomainDto[].class
            );
            return Optional.ofNullable(response.getBody());
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<UserDomainDto> findOneByEmail(String email, TokenDomain tokenDomain) {
        Map<String, String> query = new HashMap<>();
        query.put("email", email);
        Optional<UserDomainDto[]> all = findAll(query, tokenDomain);

        if (all.isPresent() && all.get().length > 0) {
            return Optional.ofNullable(all.get()[0]);
        }
        return Optional.empty();
    }

    @Override
    public boolean checkPassword(String email, String password) {
        String url = serviceConfig.getUserUrl() + "/api/users/auth/checkPassword";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        Map<String, String> postObject = new HashMap<>();
        postObject.put("email", email);
        postObject.put("password", password);

        Boolean response;
        try {
            response = restTemplate.postForObject(url, postObject, Boolean.class);
            return Boolean.TRUE.equals(response);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Optional<UserDomainDto> create(UserRegisterDto userRegisterDto, TokenDomain tokenDomain) {
        String url = serviceConfig.getUserUrl() + "/api/users";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, String> postObject = new HashMap<>();
        postObject.put("email", userRegisterDto.getEmail());
        postObject.put("password", userRegisterDto.getPassword());

        try {
            return Optional.ofNullable(restTemplate.postForObject(url, postObject, UserDomainDto.class));
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
