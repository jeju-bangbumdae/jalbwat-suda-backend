package com.backend.template.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class StoreResponseDto {
    private long id;
    private String name;
    private String address;
    private String category;
    private UserResponseDto user;
    private String lat;
    private String operationTime;
    private String lon;
    private String phone;
    private int guestBookCount;
}