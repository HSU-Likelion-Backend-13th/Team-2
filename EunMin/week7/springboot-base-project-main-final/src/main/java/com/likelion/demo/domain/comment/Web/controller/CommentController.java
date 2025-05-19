package com.likelion.demo.domain.comment.Web.controller;

import com.likelion.demo.domain.comment.Web.dto.*;
import com.likelion.demo.domain.comment.entity.Comment;
import com.likelion.demo.domain.comment.service.CommentService;
import com.likelion.demo.domain.post.web.dto.CreatePostRes;
import com.likelion.demo.global.response.SuccessResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/post/{postId}/comment")
public class CommentController {
    private final CommentService commentService;

    @PostMapping
    public ResponseEntity<SuccessResponse<?>> createComment(@PathVariable Long postId,
                                                            @RequestBody @Valid CreateCommentReq createCommentReq){
        CreateCommentRes res = commentService.create(postId, createCommentReq);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(SuccessResponse.created(res));
    }

    @GetMapping
    public ResponseEntity<SuccessResponse<?>> getAllComment(@PathVariable Long postId){
        CommentSummeryRes res = commentService.getAllByPostId(postId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(res));

    }

    @GetMapping("/{commentId}")
    public ResponseEntity<SuccessResponse<?>> getComment(@PathVariable Long commentId,
                                                         @PathVariable Long postId){
        CommentDetailRes res = commentService.getById(postId, commentId);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(res));
    }

    //http://localhost:8080/api/post/1/comment/1
    @PutMapping("/{commentId}")
    public ResponseEntity<SuccessResponse<?>> modifyComment(@PathVariable Long commentId, @PathVariable Long postId,
                                                            @RequestBody ModifyCommentReq modifyCommentReq){
        //서비스 로직
        CommentDetailRes commentDetailRes = commentService.modifyOneComment(commentId, modifyCommentReq);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.ok(commentDetailRes));
    }

    //http://localhost:8080/api/post/1/comment/1
    @DeleteMapping("/{commentId}")
    public ResponseEntity<SuccessResponse<?>> deleteComment(@PathVariable Long commentId, @PathVariable Long postId,
                                                            @RequestBody DeleteCommentReq deleteCommentReq) {
        commentService.deleteOne(commentId,deleteCommentReq);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(SuccessResponse.empty());
    }
}
