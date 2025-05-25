package com.likelion.demo.domain.comment.service;

import com.likelion.demo.domain.comment.entity.Comment;
import com.likelion.demo.domain.comment.exception.CommentNotFoundException;
import com.likelion.demo.domain.comment.repository.CommentRepository;
import com.likelion.demo.domain.comment.web.dto.*;
import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.exception.InvalidPasswordException;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl implements CommentService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

    @Override
    @Transactional
    public CreateCommentRes createOne(Long postId, CreateCommentReq createCommentReq) {
        Post post = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        Comment comment = Comment.builder()
                .post(post)
                .content(createCommentReq.getContent())
                .username(createCommentReq.getUsername())
                .password(createCommentReq.getPassword())
                .post(post)
                .build();

        Comment res = commentRepository.save(comment);

        return new CreateCommentRes(res.getId());
    }

    @Override
    @Transactional
    public CommentSummaryRes getAllByPost(Long postId) {
        postRepository.findById(postId).orElseThrow(PostNotFoundException::new);

        return new CommentSummaryRes(
                // 하나하나 보겠다??
                commentRepository.findAllByPostId(postId).stream()
                        .map(comment -> new CommentSummaryRes.CommentSummary(
                                comment.getId(),
                                comment.getContent(),
                                comment.getUsername(),
                                comment.getCreatedAt()
                        ))
                        .collect(Collectors.toList())
        );
    }

    @Override
    @Transactional
    public CommentDetailRes getById(Long postId, Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(CommentNotFoundException::new);

        if (!comment.getPost().getId().equals(postId)){
            throw new CommentNotFoundException();
        }

        return new CommentDetailRes(
                comment.getId(),
                comment.getPost().getId(),
                comment.getContent(),
                comment.getUsername(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );
    }

    @Override
    public CommentDetailRes modifyOne(Long postId, Long commentId, ModifyCommentReq modifyCommentReq) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        if(!comment.getPost().getId().equals(postId)){
            throw new CommentNotFoundException();
        }

        if(!comment.getPassword().equals(modifyCommentReq.getPassword())){
            throw new InvalidPasswordException();
        }

        comment.setContent(modifyCommentReq.getContent());
        commentRepository.save(comment);

        return new CommentDetailRes(
                comment.getId(),
                comment.getPost().getId(),
                comment.getContent(),
                comment.getUsername(),
                comment.getCreatedAt(),
                comment.getUpdatedAt()
        );

    }

    @Override
    @Transactional
    public void deleteOne(Long postId, Long commentId, DeleteCommentReq deleteCommentReq) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(CommentNotFoundException::new);

        if (!comment.getPost().getId().equals(postId)){
            throw new PostNotFoundException();
        }

        if (!comment.getPassword().equals(deleteCommentReq.getPassword())){
            throw new InvalidPasswordException();
        }

        commentRepository.delete(comment);
}
}
