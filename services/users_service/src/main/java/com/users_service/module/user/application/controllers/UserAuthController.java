package com.users_service.module.user.application.controllers;

import com.users_service.module.user.application.dtos.UserApplicationCheckPasswordQuery;
import com.users_service.module.user.application.dtos.UserApplicationDto;
import com.users_service.module.user.application.dtos.UserApplicationRegisterDto;
import com.users_service.module.user.application.facades.UserApplicationToDomainFacade;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users/auth")
public class UserAuthController {
    private final UserApplicationToDomainFacade userApplicationToDomainFacade;

    @PostMapping("checkPassword")
    public ResponseEntity<Boolean> checkPassword(@Valid @RequestBody UserApplicationCheckPasswordQuery body) {
        boolean isOk = userApplicationToDomainFacade.checkPassword(body.getEmail(), body.getPassword());
        return new ResponseEntity<>(isOk, HttpStatus.OK);
    }

    @PostMapping("register")
    public ResponseEntity<UserApplicationDto> register(@Valid @RequestBody UserApplicationRegisterDto body) {
        UserApplicationDto user = userApplicationToDomainFacade.register(body);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
