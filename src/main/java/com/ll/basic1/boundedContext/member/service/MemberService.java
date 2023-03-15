package com.ll.basic1.boundedContext.member.service;

import com.ll.basic1.base.RsData;
import com.ll.basic1.boundedContext.member.entity.Member;
import com.ll.basic1.boundedContext.member.repository.MemberRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Service
public class MemberService {
    private MemberRepository memberRepository;

    public MemberService() {
        memberRepository = new MemberRepository();
    }


    public RsData tryLogin(String username, String password) {
//        if (!password.equals("1234")) {
//            return RsData.of("F-1", "비밀번호가 일치하지 않습니다.");
//        } else if (!username.equals("user1")) {
//            return RsData.of("F-2", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
//        }
        Member member = memberRepository.findByUsername(username);

        if (member == null) {
            return RsData.of("F-2", "%s(은)는 존재하지 않는 회원입니다.".formatted(username));
        }

        if (!member.getPassword().equals(password)) {
            return RsData.of("F-1", "비밀번호가 일치하지 않습니다.");
        }


        return RsData.of("S-1", "%s 님 환영합니다.".formatted(username));
    }


    public Member findByUsername(String username) {
        return memberRepository.findByUsername(username);
    }
}
