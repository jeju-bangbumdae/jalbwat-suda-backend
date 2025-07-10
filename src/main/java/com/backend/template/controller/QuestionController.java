package com.backend.template.controller;

import com.backend.template.dto.QuestionResponseDto;
import com.backend.template.entity.Question;
import com.backend.template.repository.QuestionRepository;
import com.backend.template.service.QuestionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/questions")
@RequiredArgsConstructor
@Tag(name = "Question API", description = "질문 관련 API")
public class QuestionController {

    private final QuestionService questionService;

    @GetMapping()
    @Operation(summary = "랜덤 질문 조회", description = "데이터베이스에서 랜덤으로 질문 1개를 반환합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "랜덤 질문 조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = QuestionResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "사용 가능한 질문이 없습니다.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<QuestionResponseDto> getRandomQuestion() {
        Question randomOneQuestion = this.questionService.getRandomOneQuestion();
        return ResponseEntity.ok(new QuestionResponseDto(randomOneQuestion.getId(), randomOneQuestion.getQuestion()));
    }
}
