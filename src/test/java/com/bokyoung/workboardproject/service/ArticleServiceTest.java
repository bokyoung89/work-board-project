package com.bokyoung.workboardproject.service;

import com.bokyoung.workboardproject.domain.Article;
import com.bokyoung.workboardproject.domain.type.SearchType;
import com.bokyoung.workboardproject.dto.ArticleDto;
import com.bokyoung.workboardproject.dto.ArticleUpdateDto;
import com.bokyoung.workboardproject.repository.ArticleRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {

    @InjectMocks private ArticleService sut;

    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글을 검색하면 게시글 리스트를 반환한다.")
    @Test
    void 게시글을_검색하면_게시글_리스트를_반환한다() {
        //given

        //when
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "search keyword"); //제목, 본문, ID, 닉네임, 해시태그

        //then
       assertThat(articles).isNotNull();
    }

    @DisplayName("게시글을 조회하면 게시글을 반환한다.")
    @Test
    void 게시글을_조회하면_게시글을_반환한다() {
        //given

        //when
        ArticleDto articles = sut.searchArticle(1L); //제목, 본문, ID, 닉네임, 해시태그

        //then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글 정보를 입력하면 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSavingArticle_thenSavesArticle() {
        //given
        given(articleRepository.save(any(Article.class))).willReturn(null);

        //when
        sut.saveArticle(ArticleDto.of(LocalDateTime.now(), "Shin", "title", "content", "#java"));

        //then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글의 ID와 수정 정보를 입력하면 게시글을 수정한다.")
    @Test
    void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdatesArticle() {
        //given
        given(articleRepository.save(any(Article.class))).willReturn(null);

        //when
        sut.updateArticle(1L, ArticleUpdateDto.of("title", "content", "hashtag"));

        //then
        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글의 ID를 입력하면 게시글을 삭제한다.")
    @Test
    void givenArticleIdAndModifiedInfo_whenDeletingArticle_thenDeletesArticle() {
        //given
        willDoNothing().given(articleRepository).delete(any(Article.class));

        //when
        sut.deleteArticle(1L);

        //then
        then(articleRepository).should().delete(any(Article.class));
    }
}