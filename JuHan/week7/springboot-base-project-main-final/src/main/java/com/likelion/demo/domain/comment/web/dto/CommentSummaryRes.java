package com.likelion.demo.domain.comment.web.dto;


import java.time.LocalDateTime;
import java.util.List;

public record CommentSummaryRes(List<CommentSummary> comments) {
    public record CommentSummary(
            Long commentId,
            String content,
            String username,
            LocalDateTime createdAt

    ){}
}
