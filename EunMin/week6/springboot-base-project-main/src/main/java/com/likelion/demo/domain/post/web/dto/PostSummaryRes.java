package com.likelion.demo.domain.post.web.dto;

import java.time.LocalDateTime;
import java.util.List;

public record PostSummaryRes(
        List<PostSummary> postSummaryList
) {
    public record PostSummary(
            long id,
            String title,
            String username,
            LocalDateTime createdAt
    ){}
}
