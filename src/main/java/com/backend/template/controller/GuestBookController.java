package com.backend.template.controller;

import com.backend.template.base.authorization.TokenService;
import com.backend.template.dto.CreateGuestBookRequestDto;
import com.backend.template.dto.QnaResponseDto;
import com.backend.template.dto.UserResponseDto;
import com.backend.template.entity.GuestBook;
import com.backend.template.service.GuestBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.template.dto.GuestBookResponseDto;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api/v1/guest-books") // Your existing base path
@RequiredArgsConstructor
@Tag(name = "GuestBook API", description = "방명록 관련 API")
public class GuestBookController {

    private final GuestBookService guestBookService;
    private final TokenService tokenService;

    @PostMapping
    @Operation(summary = "방명록 생성", description = "질문, 상점 ID, 내용, 답변을 기반으로 방명록을 생성합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "방명록 생성 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 데이터", content = @Content),
            @ApiResponse(responseCode = "401", description = "인증 실패", content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 오류", content = @Content)
    })
    public ResponseEntity<String> createGuestBook(
            @RequestBody @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "방명록 생성 요청", required = true
            ) CreateGuestBookRequestDto request,
            @RequestHeader(HttpHeaders.AUTHORIZATION) String header
    ) {
        if (header == null || !header.startsWith("Bearer ")) {
            return ResponseEntity.status(401).body("인증 토큰이 유효하지 않습니다.");
        }
        String token = header.substring(7);
        Long userId = this.tokenService.validateTokenAndGetUserId(token);
        guestBookService.create(userId, request); // 토큰 그대로 전달
        return ResponseEntity.status(201).body("정상 저장 되었습니다.");
    }


    @GetMapping("/by-store/{storeId}")
    @Operation(summary = "상점 ID로 방명록 목록 조회", description = "특정 상점의 방명록 목록을 최신순으로 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "방명록 목록 조회 성공",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GuestBookResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "상점을 찾을 수 없거나 해당 상점의 방명록이 없습니다.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<GuestBookResponseDto>> getGuestBooksByStoreId(
            @PathVariable @Parameter(description = "조회할 상점의 ID", example = "1") Integer storeId) {
        List<GuestBook> guestBooks = this.guestBookService.getGuestBooksByStoreId(storeId);
        List<GuestBookResponseDto> body = new ArrayList<>();
        for (GuestBook guestBook : guestBooks) {
            body.add(this.mapToDto(guestBook));
        }
        return ResponseEntity.ok(body);
    }

    @GetMapping()
    @Operation(summary = "최신순 방명록 목록 조회", description = "전체 방명록을 최신순(생성일 기준 내림차순)으로 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "최신 방명록 목록 조회 성공",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GuestBookResponseDto.class)))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<GuestBookResponseDto>> getLatestGuestBooks() {
        List<GuestBook> guestBooks = this.guestBookService.getLatestGuestBooks();
        List<GuestBookResponseDto> body = new ArrayList<>();
        for (GuestBook guestBook : guestBooks) {
            body.add(this.mapToDto(guestBook));
        }
        return ResponseEntity.ok(body);
    }

    @GetMapping("/by-user/{userId}")
    @Operation(summary = "사용자 ID로 방명록 목록 조회", description = "특정 사용자가 작성한 방명록 목록을 최신순으로 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "방명록 목록 조회 성공",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = GuestBookResponseDto.class)))),
            @ApiResponse(responseCode = "404", description = "사용자를 찾을 수 없거나 해당 사용자의 방명록이 없습니다.",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<GuestBookResponseDto>> getGuestBooksByUserId(
            @PathVariable @Parameter(description = "조회할 사용자의 ID", example = "1") Integer userId) {
        List<GuestBook> guestBooks = this.guestBookService.getGuestBooksByUserId(userId);
        List<GuestBookResponseDto> body = new ArrayList<>();
        for (GuestBook guestBook : guestBooks) {
            body.add(this.mapToDto(guestBook));
        }
        return ResponseEntity.ok(body);
    }

    private GuestBookResponseDto mapToDto(GuestBook guestBook) {
        QnaResponseDto qnaDto = QnaResponseDto.builder()
                .question(guestBook.getQuestion().getQuestion())
                .answer(guestBook.getAnswer())
                .build();

        UserResponseDto userDto = UserResponseDto.builder()
                .id(guestBook.getUser().getId())
                .name(guestBook.getUser().getName())
                .actor(guestBook.getUser().getActor())
                .email(guestBook.getUser().getEmail())
                .build();

        return GuestBookResponseDto.builder()
                .id(guestBook.getId())
                .storeId(guestBook.getStore().getId())
                .storeName(guestBook.getStore().getName())
                .storeAddress(guestBook.getStore().getAddress())
                .storeCategory(guestBook.getStore().getCategory())
                .qna(qnaDto)
                .content(guestBook.getContent())
                .user(userDto)
                .build();
    }
}
