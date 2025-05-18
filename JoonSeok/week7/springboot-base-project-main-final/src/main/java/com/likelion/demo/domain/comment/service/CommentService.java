package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.web.dto.CommentDetailRes;
import com.likelion.demo.domain.comment.web.dto.CommentSummaryRes;
import com.likelion.demo.domain.comment.web.dto.CreateCommentReq;
import com.likelion.demo.domain.comment.web.dto.CreateCommentRes;

public interface CommentService {
    CreateCommentRes create(Long postId, CreateCommentReq createCommentReq);
    CommentSummaryRes getAllComment(Long postId);
    CommentDetailRes getComment(Long postId ,Long commentId);
}
