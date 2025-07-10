package com.backend.template.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Builder
@Getter
@AllArgsConstructor
public class GuestBookResponseDto {
    private long id;
    private long storeId;
    private String storeName;
    private String storeAddress;
    private String storeCategory;
    private QnaResponseDto qna;
    private String content;
    private UserResponseDto user;
}