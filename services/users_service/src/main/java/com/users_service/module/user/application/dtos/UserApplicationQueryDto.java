package com.users_service.module.user.application.dtos;

import lombok.Data;

import java.util.Map;

@Data
public class UserApplicationQueryDto {
    private Map<String, String> query;
}
