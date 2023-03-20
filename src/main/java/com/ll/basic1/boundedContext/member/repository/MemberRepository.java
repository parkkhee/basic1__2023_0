package com.ll.basic1.boundedContext.member.repository;

import com.ll.basic1.boundedContext.member.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUsername(String username);


//    private List<Member> members;
//
//    public MemberRepository(){
//
//        members = new ArrayList<>();
//
//        members.add(new Member("user1", "1234"));
//        members.add(new Member("abc", "12345"));
//        members.add(new Member("test", "12346"));
//        members.add(new Member("love", "12347"));
//        members.add(new Member("like", "12348"));
//        members.add(new Member("giving", "12349"));
//        members.add(new Member("thanks", "123410"));
//        members.add(new Member("hello", "123411"));
//        members.add(new Member("good", "123412"));
//        members.add(new Member("peace", "123413"));
//    }
//
//    public Member findByUsername(String username) {
//        return members
//                .stream()
//                .filter(m -> m.getUsername().equals(username))
//                .findFirst()
//                .orElse(null);
//    }
//
//    public Member findById(long userId) {
//
//        return members
//                .stream()
//                .filter(member -> member.getId() == userId)
//                .findFirst()
//                .orElse(null);
//
//    }
}
