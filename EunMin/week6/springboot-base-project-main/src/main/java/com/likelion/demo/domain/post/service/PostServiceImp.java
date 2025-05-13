package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.entity.PostState;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import com.likelion.demo.domain.post.web.dto.CreatePostReq;
import com.likelion.demo.domain.post.web.dto.CreatePostRes;
import com.likelion.demo.domain.post.web.dto.PostDetailRes;
import org.springframework.stereotype.Service;

@Service
public class PostServiceImp implements PostService {

    private final PostRepository postRepository;

    public PostServiceImp(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public CreatePostRes createOne(CreatePostReq createPostReq) {
        //게시글 작성
        //createPostReq로 Post Entity 생성
        Post post = Post.builder()
                .title(createPostReq.getTitle())
                .content(createPostReq.getContent())
                .username(createPostReq.getUsername())
                .password(createPostReq.getPassword())
                .state(PostState.PROGRESS)
                .build();

        //repository Post 저장
        Post savePost = postRepository.save(post);


        //CreatePostRes postId 리턴

        return new CreatePostRes(savePost.getId());
    }

    @Override
    public PostDetailRes getById(long postId) {
        Post post = postRepository.findById(postId)
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
}
