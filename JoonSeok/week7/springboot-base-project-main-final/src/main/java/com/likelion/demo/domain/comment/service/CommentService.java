package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.web.dto.*;

public interface CommentService {
    CreateCommentRes create(Long postId, CreateCommentReq createCommentReq);
    CommentSummaryRes getAllComment(Long postId);
    CommentDetailRes getComment(Long postId ,Long commentId);
    // 댓글 수정
    // req = 작성자 이름, 비밀번호, content
    CommentDetailRes modifyComment(Long postId, Long commentId, ModifyCommentReq modifyCommentReq);
    // 댓글 삭제
    // req = 비밀번호
    void delete(Long postId, Long commentId, DeleteCommentReq deleteCommentReq);
}
