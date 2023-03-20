package com.ll.basic1.base.initData;

import com.ll.basic1.boundedContext.article.service.ArticleService;
import com.ll.basic1.boundedContext.member.service.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration  //컴포넌트랑 비슷한 역할  최초에 한번 bean 객체 만들고,
@Profile({"dev","test"}) //오직 dev환경과 test 환경에서만
public class NotProd {

    @Bean
    public CommandLineRunner initData(MemberService memberService, ArticleService articleService) {
        return args -> {

            memberService.join("user1", "1234");
            memberService.join("abc ", "12345");
            memberService.join("test ", "12346");
            memberService.join("love ", "12347");
            memberService.join("like ", "12348");
            memberService.join("giving ", "12349");
            memberService.join("thanks ", "123410");
            memberService.join("hello ", "123411");
            memberService.join("good ", "123412");
            memberService.join("peace ", "123413");


        };

    }
}
