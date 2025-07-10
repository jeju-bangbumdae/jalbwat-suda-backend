package com.backend.template.controller;

import com.backend.template.dto.StoreResponseDto;
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

import java.util.List;
import java.util.Optional;

@RestController()
@RequestMapping("/api/v1/stores")
@RequiredArgsConstructor
@Tag(name = "Store API", description = "상점 관련 API")
public class StoreController {

    @GetMapping
    @Operation(summary = "매장 목록 조회", description = "위도, 경도, 카테고리를 기준으로 매장 목록을 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매장 목록 조회 성공",
                    content = @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = StoreResponseDto.class)))),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<List<StoreResponseDto>> getStores(
            @RequestParam @Parameter(description = "사용자 현재 위도 (필수 사항)", example = "37.5665") Double lat,
            @RequestParam @Parameter(description = "사용자 현재 경도 (필수 사항)", example = "126.9780") Double lon,
            @RequestParam(required = false) @Parameter(description = "매장 카테고리 (선택 사항, 예: Cafe, Restaurant, Bakery)", example = "Cafe") String category) {

        Optional<Double> optionalLat = Optional.ofNullable(lat);
        Optional<Double> optionalLon = Optional.ofNullable(lon);
        Optional<String> optionalCategory = Optional.ofNullable(category);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/{storeId}")
    @Operation(summary = "단일 매장 조회", description = "상점 ID로 특정 매장 정보를 조회합니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "매장 조회 성공",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = StoreResponseDto.class))),
            @ApiResponse(responseCode = "404", description = "매장을 찾을 수 없음",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "서버 오류",
                    content = @Content(mediaType = "application/json"))
    })
    public ResponseEntity<StoreResponseDto> getStoreById(
            @PathVariable @Parameter(description = "조회할 상점의 ID", example = "1") Long storeId) {
        return ResponseEntity.ok(null);
    }
}
