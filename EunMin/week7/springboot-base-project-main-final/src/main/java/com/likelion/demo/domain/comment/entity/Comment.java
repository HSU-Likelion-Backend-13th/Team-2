package com.likelion.demo.domain.comment.entity;

import com.likelion.demo.domain.post.entity.Post;
import com.likelion.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    private Post post;


}
