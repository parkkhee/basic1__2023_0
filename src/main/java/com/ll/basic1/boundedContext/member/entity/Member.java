package com.ll.basic1.boundedContext.member.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString
@Entity
@EntityListeners(AuditingEntityListener.class)  //날짜체크위해서는
@Builder
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
//    private static long lastid;
    @CreatedDate // 자동으로 날짜
    private LocalDateTime createDate;
    @LastModifiedDate //갱신된 날짜 삽입
    private LocalDateTime modifyDate;
    @Column(unique = true)
    private String username;
    private String password;

//
//    static {
//        lastid=0;
//    }

//    public Member(String username, String password) {
//
//        this(++lastid, username, password);
//    }


}
