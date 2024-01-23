package com.users_service.module.user.domain.services;

import com.users_service.module.user.domain.dtos.UserDomainDto;
import com.users_service.module.user.domain.facades.UserDomainToDataFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserPasswordService {
    private final UserDomainToDataFacade dataFacade;
    private final PasswordEncoder passwordEncoder;
    public String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }

    public boolean checkPassword(String email, String password) {
        Optional<UserDomainDto> user = dataFacade.findOneByEmail(email);
        return user.filter(userDomainDto -> passwordEncoder.matches(password, userDomainDto.getPassword())).isPresent();
    }

    public boolean checkPassword(Long userId, String password) {
        Optional<UserDomainDto> user = dataFacade.findOneById(userId);
        return user.filter(userDomainDto -> passwordEncoder.matches(password, userDomainDto.getPassword())).isPresent();
    }
}
