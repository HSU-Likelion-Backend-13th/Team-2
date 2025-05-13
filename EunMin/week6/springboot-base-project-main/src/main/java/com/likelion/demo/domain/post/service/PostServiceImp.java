package com.likelion.demo.domain.post.service;

import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.domain.post.entity.PostState;
import com.likelion.demo.domain.post.exception.InvalidPasswordException;
import com.likelion.demo.domain.post.exception.PostNotFoundException;
import com.likelion.demo.domain.post.repository.PostRepository;
import com.likelion.demo.domain.post.web.dto.*;
import com.likelion.demo.domain.post.web.dto.PostSummaryRes.PostSummary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Override
    public PostSummaryRes getAll() {
        List<Post> posts = postRepository.findAll();

        List<PostSummary> postSummaryList = new ArrayList<>();
        for(Post post : posts){
            PostSummary postSummary = new PostSummary(
                    post.getId(),
                    post.getTitle(),
                    post.getUsername(),
                    post.getCreatedAt()
            );

            postSummaryList.add(postSummary);
        }
        return new PostSummaryRes(postSummaryList);

    }


    @Transactional
    @Override
    public PostDetailRes modifyOne(long postId, ModifyPostReq modifyPostReq) {
        Post foundPost = postRepository.findById(postId)
                .orElseThrow(PostNotFoundException::new);

        if(!foundPost.getPassword().equals(modifyPostReq.getPassword())){
            throw new InvalidPasswordException();
        }

        foundPost.modify(modifyPostReq.getTitle(), modifyPostReq.getContent());
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
}
