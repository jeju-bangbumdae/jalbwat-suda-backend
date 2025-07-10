package com.backend.template.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@AllArgsConstructor
public class QnaResponseDto {
    private String question;
    private String answer;
}