package com.likelion.demo.domain.comment.Web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ModifyCommentReq {
    private String content;
    private String password;
}
