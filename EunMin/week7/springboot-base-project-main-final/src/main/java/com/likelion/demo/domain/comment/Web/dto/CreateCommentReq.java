package com.likelion.demo.domain.comment.Web.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateCommentReq {
    
    @NotBlank(message = "내용은 필수입니다.")
    private String content;

    @NotBlank(message = "사용자이름은 필수입니다.")
    private String username;

    @NotBlank(message = "비밀번호는 필수입니다.")
    private String password;
}
