package com.backend.template.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class UserResponseDto {
    private long id;
    private String email;
    private String name;
    private String actor;
}