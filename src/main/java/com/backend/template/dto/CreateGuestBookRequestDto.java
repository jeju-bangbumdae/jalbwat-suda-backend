package com.backend.template.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class CreateGuestBookRequestDto {
    private Long questionId;
    private String answer;
    private Long storeId;
    private String content;
}