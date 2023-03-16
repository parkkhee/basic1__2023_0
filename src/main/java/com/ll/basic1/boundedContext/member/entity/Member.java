package com.ll.basic1.boundedContext.member.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public class Member {
    private final long id;
    private static long lastid;

    private String username;
    private String password;


    static {
        lastid=0;
    }

    public Member(String username, String password) {

        this(++lastid, username, password);
    }


}
