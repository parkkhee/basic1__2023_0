package com.ll.basic1.boundedContext.article.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)  //날짜체크위해서는
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //auto_increment
    private long id;

    @CreatedDate // 자동으로 날짜
    private LocalDateTime createDate;

    @LastModifiedDate //갱신된 날짜 삽입
    private LocalDateTime modifyDate;
    private String title;
    private String body;
}
