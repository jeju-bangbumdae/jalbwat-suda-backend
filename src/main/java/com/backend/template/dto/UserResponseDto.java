package com.backend.template.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserResponseDto {
    private long id;
    private String name;
    private String actor;
}