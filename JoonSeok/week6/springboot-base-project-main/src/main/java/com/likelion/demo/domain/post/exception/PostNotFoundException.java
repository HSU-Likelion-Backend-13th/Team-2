package com.likelion.demo.domain.post.exception;

import com.likelion.demo.global.exception.BaseException;

public class PostNotFoundException extends BaseException {
    public PostNotFoundException() {
        super(PostErrorCode.POST_NIT_FOUND_404);
    }
}
