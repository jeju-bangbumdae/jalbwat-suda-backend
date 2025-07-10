package com.backend.template.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.backend.template.dto.GuestBookResponseDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/v1/guest-books") // Your existing base path
@RequiredArgsConstructor
@Tag(name = "GuestBook API", description = "방명록 관련 API")
public class GuestBookController {

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
            @PathVariable @Parameter(description = "조회할 상점의 ID", example = "1") Long storeId) {
        return ResponseEntity.ok(null);
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
        return ResponseEntity.ok(null);
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
            @PathVariable @Parameter(description = "조회할 사용자의 ID", example = "1") Long userId) {
        return ResponseEntity.ok(null);
    }
}
