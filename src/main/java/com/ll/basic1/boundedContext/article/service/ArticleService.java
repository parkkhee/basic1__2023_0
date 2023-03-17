package com.ll.basic1.boundedContext.article.service;

import com.ll.basic1.base.RsData;
import com.ll.basic1.boundedContext.article.entity.Article;
import com.ll.basic1.boundedContext.article.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ArticleService {

    private final ArticleRepository articleRepository;

    public Article write(String title, String body) {

        Article article = Article
                .builder()
//                .createDate(LocalDateTime.now())
//                .modifyDate(LocalDateTime.now())
                .title(title)
                .body(body)
                .build();

        articleRepository.save(article);  //insert 혹은 업데이트가 일어난다.

        return article;

    }

}
