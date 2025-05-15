package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.entity.PostState;
import com.likelion.demo.domain.post.exception.InvalidPasswordException;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import com.likelion.demo.domain.post.web.dto.PostSummeryRes;
import com.likelion.demo.domain.post.web.dto.PostSummeryRes.PostSummery;
import com.likelion.demo.domain.post.web.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    // 게시글 작성
    @Override
    public CreatePostRes createOne(CreatePostReq createPostReq) {
        // 1. createPostReq가지고 Post Entity 생성
        Post post = Post.builder()
                .title(createPostReq.getTitle())
                .content(createPostReq.getContent())
                .username((createPostReq.getUsername()))
                .password(createPostReq.getPassword())
                .state(PostState.PROGRESS)
                .build();

        // 2. repository Post 저장 (postRepository 사용)
        Post savedPost = postRepository.save(post);

        // 3. 반환 CreatePostRes
        return new CreatePostRes(savedPost.getId());
    }
    // 게시글 단건 조회
    @Override
    public PostDetailRes getById(Long postId) {
        // 1. postId에 해당하는 Post - DB에서 조회
        Post post = postRepository.findById(postId)
                // 404 - error
                .orElseThrow(PostNotFoundException::new);
        return new PostDetailRes(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUsername(),
                post.getPassword(),
                post.getState(),
                post.getCreatedAt(),
                post.getUpdatedAt()
        );
    }

    @Override
    public PostSummeryRes getByall() {
        // 1. DB에 있는 Post 전체 조회(postRepository 사용)
        List<Post> posts = postRepository.findAll();
        // 2. posts -> postSummeryRes
        List<PostSummery> postSummaryList = new ArrayList<>();
        for (Post post : posts) {
            PostSummery postSummery = new PostSummery(
                    post.getId(),
                    post.getTitle(),
                    post.getUsername(),
                    post.getCreatedAt()
            );
            postSummaryList.add(postSummery);

        }
        // 3. 반환
        return new PostSummeryRes(postSummaryList);
    }

    // 게시글 수정
    @Transactional
    @Override
    public PostDetailRes modifyOne(Long postId, ModifyPostReq modifyPostReq) {
        // 1. DB에 있는 PostId 로 Post 찾기
        Post foundPost = postRepository.findById(postId)
        // 404 - error
                .orElseThrow(PostNotFoundException::new);
        // 2. 비밀번호 검증
        // 403 - error
        if (!foundPost.getPassword().equals(modifyPostReq.getPassword())) {
            throw new InvalidPasswordException();
        }
        // 3. post 수정
        foundPost.modify(modifyPostReq.getTitle(),modifyPostReq.getContent());

        // postRepository 반환
        return new PostDetailRes(
                foundPost.getId(),
                foundPost.getTitle(),
                foundPost.getContent(),
                foundPost.getUsername(),
                foundPost.getPassword(),
                foundPost.getState(),
                foundPost.getCreatedAt(),
                foundPost.getUpdatedAt()
        );
    }

    @Transactional
    @Override
    public void deleteOne(Long postId, DeletePostReq deletePostReq) {
        // 1. DB에 있는 PostId 로 Post 찾기
        Post post = postRepository.findById(postId)
                // 404 - 게시글이 존재 하지 않음
                .orElseThrow(PostNotFoundException::new);
        // 2. 비밀번호 검증
        // 403 - 비밀번호 불일치
        if (!post.getPassword().equals(deletePostReq.getPassword())) {
            throw new InvalidPasswordException();
        }
        // 3. post 삭제
        postRepository.delete(post);


    }

}
