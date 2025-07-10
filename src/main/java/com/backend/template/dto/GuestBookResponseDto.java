package com.backend.template.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class GuestBookResponseDto {
    private long id;
    private long storeId;
    private String name;
    private String address;
    private String category;
    private QnaResponseDto qna;
    private String content;
    private UserResponseDto user;
}