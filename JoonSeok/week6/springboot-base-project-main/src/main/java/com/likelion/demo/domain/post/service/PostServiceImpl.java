package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.entity.PostState;
import com.likelion.demo.domain.post.repository.PostRepository;
import com.likelion.demo.domain.post.web.DTO.CreatePostReq;
import com.likelion.demo.domain.post.web.DTO.CreatePostRes;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    // 게시글 작성
    @Override
    public CreatePostRes createOne(CreatePostReq createPostReq) {
        // 1. createPostReq로 Post Entity 생성
        Post post = Post.builder()
                .title(createPostReq.getTitle())        // Post request에서 게시글에 관한
                .content(createPostReq.getContent())    // 정보를 가져와서
                .username(createPostReq.getUsername())  // 서비스 계층에서 Post 생성
                .password(createPostReq.getPassword())
                .state(PostState.PROGRESS)
                .build();

        // 2. repository Post 저장
        Post savedPost = postRepository.save(post);

        // 3. CreatePostRes 반환
        return new CreatePostRes(savedPost.getId());
    }
}
