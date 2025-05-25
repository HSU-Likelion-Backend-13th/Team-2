package com.likelion.demo.domain.comment.Web.dto;

import java.time.LocalDateTime;

public record CommentDetailRes(
        Long commentId,
        Long postId,
        String content,
        String username, LocalDateTime createAt,
        LocalDateTime updateAt
) {
}
