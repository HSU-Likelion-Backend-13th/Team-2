package com.likelion.demo.domain.comment.Web.dto;

import com.likelion.demo.domain.comment.entity.Comment;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Builder
public record CommentSummeryRes(
        List<CommentSummery> comments
) {
    public record CommentSummery(
            Long commentId,
            String content,
            String username,
            LocalDateTime createAt
    ) {}
}
