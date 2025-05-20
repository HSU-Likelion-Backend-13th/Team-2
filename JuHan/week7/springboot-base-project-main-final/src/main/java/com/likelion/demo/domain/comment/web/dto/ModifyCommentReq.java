package com.likelion.demo.domain.comment.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
public class ModifyCommentReq {
    private String content;
    private String password;
}
