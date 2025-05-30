package com.likelion.demo.domain.post.web.controller;

import com.likelion.demo.domain.post.service.PostService;
import com.likelion.demo.domain.post.web.dto.*;
import com.likelion.demo.global.response.SuccessResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/post")
@RestController //JSON 반환 시 필요하다.
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    //게시글 작성
    @PostMapping
    public ResponseEntity<SuccessResponse<?>> CreatePost(@RequestBody @Valid CreatePostReq createPostReq){
        //서비스 계층에게 기능 넘겨준다.
        CreatePostRes createPostRes = postService.createOne(createPostReq);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(createPostRes));
    }

    //게시글 단건 조회
    @GetMapping("/{postId}")
    public ResponseEntity<SuccessResponse<?>> getPostById(@PathVariable long postId){
        //서비스 로직
        PostDetailRes postDetailRes = postService.getById(postId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(postDetailRes));
    }


    //게시글 전체 조회
    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getAllPosts(){
        PostSummaryRes postSummaryRes = postService.getAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(postSummaryRes));
    }


    //게시글 수정
    @PutMapping("/{postId}")
    public ResponseEntity<SuccessResponse<?>> modifyPost(@PathVariable long postId,
                                                         @RequestBody ModifyPostReq modifyPostReq){
        //서비스
        PostDetailRes postDetailRes = postService.modifyOne(postId, modifyPostReq);


        //반환
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(postDetailRes));


    }



    //게시글 삭제
    @DeleteMapping("/{postId}")
    public ResponseEntity<SuccessResponse<?>> deletePost(@PathVariable long postId
        ,@RequestBody DeletePostReq deletePostReq){
        postService.deleteOne(postId, deletePostReq);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.empty());
                //.body(SuccessResponse.ok(null));
    }

}
