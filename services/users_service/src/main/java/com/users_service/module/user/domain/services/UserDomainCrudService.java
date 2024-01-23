package com.users_service.module.user.domain.services;

import com.users_service.module.user.domain.dtos.UserDomainCreateDto;
import com.users_service.module.user.domain.dtos.UserDomainDto;
import com.users_service.module.user.domain.dtos.UserDomainQueryDto;
import com.users_service.module.user.domain.dtos.UserDomainUpdateDto;
import com.users_service.module.user.domain.facades.UserDomainToDataFacade;
import com.users_service.module.user.domain.interfaces.UserCrudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDomainCrudService implements UserCrudService {
    private final UserDomainToDataFacade userDomainToDataFacade;
    private final UserPasswordService passwordService;
    @Override
    public List<UserDomainDto> findAll(UserDomainQueryDto queryDto) {
        return userDomainToDataFacade.findAll(queryDto);
    }

    @Override
    public Optional<UserDomainDto> findOneById(Long id) {
        return userDomainToDataFacade.findOneById(id);
    }

    public Optional<UserDomainDto> findOneByEmail(String email) {
        return userDomainToDataFacade.findOneByEmail(email);
    }

    @Override
    public UserDomainDto create(UserDomainCreateDto dto) {
        dto.setPassword(passwordService.encodePassword(dto.getPassword()));
        return userDomainToDataFacade.create(dto);
    }
    @Override
    public UserDomainDto update(Long id, UserDomainUpdateDto dto) {
        return userDomainToDataFacade.update(id, dto);
    }

    @Override
    public void delete(Long id) {
        userDomainToDataFacade.delete(id);
    }
}
