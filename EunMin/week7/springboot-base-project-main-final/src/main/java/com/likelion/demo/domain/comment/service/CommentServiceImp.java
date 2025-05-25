package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.Web.dto.*;
import com.likelion.demo.domain.comment.entity.Comment;
import com.likelion.demo.domain.comment.exception.CommentInvalidPassword;
import com.likelion.demo.domain.comment.exception.CommentNotFoundException;
import com.likelion.demo.domain.comment.repository.CommentRepository;
import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImp implements CommentService {
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public CreateCommentRes create(Long postId, CreateCommentReq createCommentReq) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        Comment comment = Comment.builder()
                .post(post)
                .content(createCommentReq.getContent())
                .username(createCommentReq.getUsername())
                .password(createCommentReq.getPassword())
                .build();

        Comment res = commentRepository.save(comment);

        return new CreateCommentRes(res.getId());
    }

    @Override
    public CommentSummeryRes getAllByPostId(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        return new CommentSummeryRes(
                commentRepository.findAllByPostId(postId).stream()
                        .map(c->new CommentSummeryRes.CommentSummery(
                                c.getId(),
                                c.getContent(),
                                c.getUsername(),
                                c.getCreatedAt()
                        ))
                        .collect(Collectors.toList())
        );
    }

    @Override
    public CommentDetailRes getById(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        if(!comment.getPost().getId().equals(postId)){
            throw new CommentNotFoundException();
        }
        return new  CommentDetailRes(
                comment.getId(),
                comment.getPost().getId(),
                comment.getContent(),
                comment.getUsername(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

    @Transactional
    @Override
    public CommentDetailRes modifyOneComment(Long commentId, ModifyCommentReq modifyCommentReq) {
        //DB에서 commentID로 해당 댓글이 존재하는지 보고 찾아옴.
        Comment foundComment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        //비밀번호 검증
        if(!foundComment.getPassword().equals(modifyCommentReq.getPassword())){
            throw new CommentInvalidPassword();
        }

        //댓글 수정
        foundComment.setContent(modifyCommentReq.getContent());

        //수정된 댓글 반환
        return new CommentDetailRes(
                foundComment.getId(),
                foundComment.getPost().getId(),
                foundComment.getContent(),
                foundComment.getUsername(),
                foundComment.getCreatedAt(),
                foundComment.getUpdatedAt()
        );
    }

    @Transactional
    @Override
    public void deleteOne(Long commentId, DeleteCommentReq deleteCommentReq) {

        //삭제할 댓글이 존재하는지 확인
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        //비밀번호 검증
        if(!comment.getPassword().equals(deleteCommentReq.getPassword())){
            throw new CommentInvalidPassword();
        }

        //댓글 삭제
        commentRepository.delete(comment);
    }
}
