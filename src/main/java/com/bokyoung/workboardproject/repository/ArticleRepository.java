package com.bokyoung.workboardproject.repository;

import com.bokyoung.workboardproject.domain.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {
}
