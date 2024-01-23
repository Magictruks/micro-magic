package com.users_service.module.user.application.facades;

import com.users_service.core.interfaces.CrudFacade;
import com.users_service.module.user.application.dtos.UserApplicationCreateDto;
import com.users_service.module.user.application.dtos.UserApplicationDto;
import com.users_service.module.user.application.dtos.UserApplicationRegisterDto;
import com.users_service.module.user.application.dtos.UserApplicationUpdateDto;
import com.users_service.module.user.application.mappers.UserApplicationCrudMapper;
import com.users_service.module.user.domain.dtos.UserDomainCreateDto;
import com.users_service.module.user.domain.dtos.UserDomainDto;
import com.users_service.module.user.domain.dtos.UserDomainQueryDto;
import com.users_service.module.user.domain.dtos.UserDomainUpdateDto;
import com.users_service.module.user.domain.services.UserDomainCrudService;
import com.users_service.module.user.domain.services.UserPasswordService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserApplicationToDomainFacade implements CrudFacade<UserApplicationDto, UserApplicationCreateDto, UserApplicationUpdateDto, Map<String, String>> {
    private final UserDomainCrudService userCRUDService;
    private final UserPasswordService userPasswordService;
    private final UserApplicationCrudMapper userApplicationCRUDMapper;
    public List<UserApplicationDto> findAll(Map<String, String> query) {
        UserDomainQueryDto queryDto = userApplicationCRUDMapper.queryToDomainQuery(query);
        List<UserDomainDto> data = userCRUDService.findAll(queryDto);
        return userApplicationCRUDMapper.dtosFromDatas(data);
    }

    public Optional<UserApplicationDto> findOneById(Long id) {
        Optional<UserDomainDto> data = userCRUDService.findOneById(id);
        return data.map(userApplicationCRUDMapper::dtoFromData);
    }

    public UserApplicationDto create(UserApplicationCreateDto dto) {
        UserDomainCreateDto mapperDtoToData = userApplicationCRUDMapper.createDtoToDomain(dto);
        UserDomainDto userDomainCreateDto = userCRUDService.create(mapperDtoToData);
        return userApplicationCRUDMapper.dtoFromData(userDomainCreateDto);
    }

    public UserApplicationDto update(Long id, UserApplicationUpdateDto dto) {
        UserDomainUpdateDto mapperDtoToData = userApplicationCRUDMapper.updateDtoToData(dto);
        UserDomainDto userDomainCreateDto = userCRUDService.update(id, mapperDtoToData);
        return userApplicationCRUDMapper.dtoFromData(userDomainCreateDto);
    }

    public void delete(Long id) {
        userCRUDService.delete(id);
    }

    public boolean checkPassword(String email, String password) {
        return userPasswordService.checkPassword(email, password);
    }

    public UserApplicationDto register(UserApplicationRegisterDto body) {
        UserDomainCreateDto mapperDtoToData = userApplicationCRUDMapper.registerDtoToDomain(body);
        UserDomainDto userDomainCreateDto = userCRUDService.create(mapperDtoToData);
        return userApplicationCRUDMapper.dtoFromData(userDomainCreateDto);
    }
}
