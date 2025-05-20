package com.likelion.demo.domain.comment.exception;

import com.likelion.demo.global.exception.BaseException;

public class CommentInvalidException extends BaseException {
    public CommentInvalidException() {
        super(CommentErrorCode.COMMENT_PASSWWORD_INVALID_403);
    }
}
