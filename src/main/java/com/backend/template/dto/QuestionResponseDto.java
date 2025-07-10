package com.backend.template.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class QuestionResponseDto {
    private String question;
    private String answer;
}