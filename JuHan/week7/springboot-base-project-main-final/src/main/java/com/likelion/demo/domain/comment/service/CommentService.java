package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.web.dto.*;

public interface CommentService {

    // 댓글 작성
    CreateCommentRes createOne(Long postId, CreateCommentReq createCommentReq);

    // 댓글 전체 조회
    CommentSummaryRes getAllByPost(Long postId);

    // 댓글 단건 조회
    CommentDetailRes getById(Long postId, Long commentId);

    // 댓글 수정
    CommentDetailRes modifyOne(Long postId, Long commentId, ModifyCommentReq modifyCommentReq);
    // 댓글 삭제
    void deleteOne(Long postId, Long commentId, DeleteCommentReq deleteCommentReq);
}
