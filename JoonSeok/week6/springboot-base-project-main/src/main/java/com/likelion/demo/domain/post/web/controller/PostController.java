package com.likelion.demo.domain.post.web.controller;

import com.likelion.demo.domain.post.service.PostService;
import com.likelion.demo.domain.post.web.DTO.CreatePostReq;
import com.likelion.demo.domain.post.web.DTO.CreatePostRes;
import com.likelion.demo.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor // final, not null 속성의 매개변수를 가지는 생성자 어노테이션
public class PostController {
    // 의존성 부여
    private final PostService postService;
    // 게시글 작성
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> CreatePost(@RequestBody @Valid CreatePostReq createPostReq) {
        // 서비스 계층 위임
        CreatePostRes createPostRes = postService.createOne(createPostReq);
        // 반환
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(createPostRes));
    }

    // 게시글 단건 조회

    // 게시글 전체 조회

    // 게시글 수정

    // 게시글 삭제
}
