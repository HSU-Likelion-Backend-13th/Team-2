package com.likelion.demo.domain.comment.web.dto;

import lombok.Getter;

@Getter
public class ModifyCommentReq {
    private String username;
    private String password;
    private String content;
}
