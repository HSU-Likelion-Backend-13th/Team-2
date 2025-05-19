package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.Web.dto.*;

public interface CommentService {
    CreateCommentRes create(Long postId, CreateCommentReq createCommentReq);
    CommentSummeryRes getAllByPostId(Long postId);
    CommentDetailRes getById(Long id, Long commentId);

    CommentDetailRes modifyOneComment(Long commentId, ModifyCommentReq modifyCommentReq);

    void deleteOne(Long commentId, DeleteCommentReq deleteCommentReq);
}
