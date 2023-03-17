package com.ll.basic1.boundedContext.article.repository;

import com.ll.basic1.boundedContext.article.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article,Long> {  //상속받아서 자동적으로 레포지토리로 여겨진다. 어노테이션 불필요?


}
