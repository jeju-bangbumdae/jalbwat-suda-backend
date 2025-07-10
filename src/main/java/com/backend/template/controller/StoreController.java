package com.backend.template.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Tag(name = "Store API", description = "상점 관련 API")
public class StoreController {

    private final String IMAGE_STORAGE_LOCATION = "C:/path/to/your/image/directory/";

//    @GetMapping("/{imageName}")
//    public ResponseEntity<byte[]> read(@PathVariable String imageName) {
//        try {
//            // 1. 이미지 파일의 경로를 생성합니다.
//            Path imagePath = Paths.get(IMAGE_STORAGE_LOCATION, imageName);
//
//            // 2. 파일이 존재하는지, 읽을 수 있는지 확인합니다.
//            if (!Files.exists(imagePath) || !Files.isReadable(imagePath)) {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND); // 파일이 없거나 읽을 수 없으면 404 반환
//            }
//
//            // 3. 이미지를 바이트 배열로 읽어옵니다.
//            byte[] imageBytes = Files.readAllBytes(imagePath);
//
//            // 4. Content-Type 헤더를 설정하여 브라우저가 이미지임을 알 수 있도록 합니다.
//            //    실제 이미지 타입에 따라 MediaType을 변경해야 합니다 (예: JPG, PNG, GIF 등).
//            //    여기서는 파일 확장자를 기반으로 동적으로 MediaType을 결정하는 로직을 추가할 수 있습니다.
//            String contentType = getContentType(imageName);
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.parseMediaType(contentType));
//            headers.setContentLength(imageBytes.length); // 이미지 크기 설정 (선택 사항이지만 좋음)
//
//            // 5. 바이트 배열과 헤더를 포함한 ResponseEntity를 반환합니다.
//            return new ResponseEntity<>(imageBytes, headers, HttpStatus.OK);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR); // 파일 읽기 오류 시 500 반환
//        }
//    }
//
//    // 파일 확장자를 기반으로 Content-Type을 결정하는 헬퍼 메서드
//    private String getContentType(String imageName) {
//        if (imageName.endsWith(".jpg") || imageName.endsWith(".jpeg")) {
//            return "image/jpeg";
//        } else if (imageName.endsWith(".png")) {
//            return "image/png";
//        } else if (imageName.endsWith(".gif")) {
//            return "image/gif";
//        }
//        // 기본값 또는 알 수 없는 경우
//        return MediaType.APPLICATION_OCTET_STREAM_VALUE; // 일반 바이너리 데이터
//    }
}
