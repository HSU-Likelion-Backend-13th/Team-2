package com.likelion.demo.domain.post.entity;

import com.likelion.demo.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@NoArgsConstructor //기본생성자 생성
@Builder
@AllArgsConstructor //모든 멤버변수를 매개변수로 가지는 생성자
public class Post extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String content;
    private String username;
    private String password;

    @Enumerated(EnumType.STRING)
    private PostState state;

    public void modify(String title, String content){
        this.title = title;
        this.content = content;
    }
}
