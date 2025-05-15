package com.likelion.demo.domain.post.web.dto;

import java.time.LocalDateTime;
import java.util.List;


public record PostSummeryRes(List<PostSummery> postSummeryList) {

    public record PostSummery(
            Long id,
            String title,
            String username,
            LocalDateTime createdAt
    ){

    }
}

