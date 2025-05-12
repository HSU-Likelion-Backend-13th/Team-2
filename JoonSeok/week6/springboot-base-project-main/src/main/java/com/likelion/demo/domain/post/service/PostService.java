package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.web.DTO.CreatePostReq;
import com.likelion.demo.domain.post.web.DTO.CreatePostRes;
import com.likelion.demo.domain.post.web.DTO.PostDetailRes;

public interface PostService {
    // 게시글 작성
    CreatePostRes createOne(CreatePostReq createPostReq);
    PostDetailRes getById(Long postId);
}
