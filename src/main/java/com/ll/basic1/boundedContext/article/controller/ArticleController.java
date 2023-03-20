package com.ll.basic1.boundedContext.article.controller;

import com.ll.basic1.base.RsData;
import com.ll.basic1.boundedContext.article.entity.Article;
import com.ll.basic1.boundedContext.article.repository.ArticleRepository;
import com.ll.basic1.boundedContext.article.service.ArticleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/article")
@RequiredArgsConstructor  //final 붙은 것만 생성자 만들어준다.
public class ArticleController {
    private final ArticleService articleService;

    @GetMapping("/write")
    @ResponseBody
    public RsData write(String title, String body) {

        if (title == null || title.trim().length() == 0) {
            return RsData.of("F-1", "title(을)를 입력해주세요.");
        } else if (body == null || body.trim().length() == 0) {
            return RsData.of("F-2", "body(을)를 입력해주세요.");
        }

        Article servicedArticle = articleService.write(title, body);


        return RsData.of("S-1","1번 글이 생성되었습니다.",servicedArticle);
    }

}


