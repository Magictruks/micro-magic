package com.users_service.module.user.domain.facades;


import com.users_service.core.interfaces.CrudFacade;
import com.users_service.module.user.data.models.User;
import com.users_service.module.user.data.repositories.UserCrudRepository;
import com.users_service.module.user.domain.dtos.UserDomainCreateDto;
import com.users_service.module.user.domain.dtos.UserDomainDto;
import com.users_service.module.user.domain.dtos.UserDomainQueryDto;
import com.users_service.module.user.domain.dtos.UserDomainUpdateDto;
import com.users_service.module.user.domain.mappers.UserDomainCrudMapper;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserDomainToDataFacade implements CrudFacade<UserDomainDto, UserDomainCreateDto, UserDomainUpdateDto, UserDomainQueryDto> {
    private final UserCrudRepository userCRUDRepository;
    private final UserDomainCrudMapper userDomainCRUDMapper;
    public List<UserDomainDto> findAll(UserDomainQueryDto queryDto) {
        List<User> data;
        if (Strings.isNotEmpty(queryDto.getEmail())) {
            data = userCRUDRepository.findAllByEmail(queryDto.getEmail());
        } else {
            data = userCRUDRepository.findAll();
        }
        return userDomainCRUDMapper.dtosFromDatas(data);
    }

    public Optional<UserDomainDto> findOneById(Long id) {
        Optional<User> data = userCRUDRepository.findById(id);
        return data.map(userDomainCRUDMapper::dtoFromData).or(Optional::empty);
    }

    public Optional<UserDomainDto> findOneByEmail(String email) {
        Optional<User> data = userCRUDRepository.findUserByEmail(email);
        return data.map(userDomainCRUDMapper::dtoFromData).or(Optional::empty);
    }
    public UserDomainDto create(UserDomainCreateDto dto) {
        User mapperDtoToData = userDomainCRUDMapper.createDtoToData(dto);
        User save = userCRUDRepository.save(mapperDtoToData);
        return userDomainCRUDMapper.dtoFromData(save);
    }

    public UserDomainDto update(Long id, UserDomainUpdateDto dto) {
        Optional<User> userDB = userCRUDRepository.findById(id);

        if (userDB.isEmpty()) {
            return null;
        }

        User user = userDB.get()
                .setEmail(dto.getEmail());

        userCRUDRepository.save(user);
        return userDomainCRUDMapper.dtoFromData(user);
    }

    public void delete(Long id) {
        userCRUDRepository.deleteById(id);
    }
}
