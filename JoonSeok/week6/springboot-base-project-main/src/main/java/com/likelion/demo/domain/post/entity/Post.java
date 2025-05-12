package com.likelion.demo.domain.post.entity;

import com.likelion.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Builder // 모든 필드가 생성자가 있어야 함
@AllArgsConstructor // 모든 필드 생성자 생성
public class Post extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;
    private String username;
    private String password;
    // 작성일시, 수정일시

    @Enumerated(EnumType.STRING)
    private PostState state;

}
