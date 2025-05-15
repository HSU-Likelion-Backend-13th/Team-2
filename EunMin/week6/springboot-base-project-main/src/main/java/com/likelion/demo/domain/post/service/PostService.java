package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.web.dto.*;

public interface PostService {

    //게시글 작성
    CreatePostRes createOne(CreatePostReq createPostReq);

    //게시글 단건 조회
    PostDetailRes getById(long postId);

    //게시글 전체 조회
    PostSummaryRes getAll();

    //게시글 수정
    PostDetailRes modifyOne(long postId, ModifyPostReq modifyPostReq);

    void deleteOne(long postId, DeletePostReq deletePostReq);
}
