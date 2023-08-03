package com.bokyoung.workboardproject.service;

import com.bokyoung.workboardproject.domain.Article;
import com.bokyoung.workboardproject.domain.ArticleComment;
import com.bokyoung.workboardproject.dto.ArticleCommentDto;
import com.bokyoung.workboardproject.dto.ArticleDto;
import com.bokyoung.workboardproject.repository.ArticleCommentRepository;
import com.bokyoung.workboardproject.repository.ArticleRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {

    @InjectMocks private ArticleCommentService sut;

    @Mock private ArticleCommentRepository articleCommentRepository;
    @Mock private ArticleRepository articleRepository;

    @DisplayName("게시글을 ID로 조회하면 해당하는 댓글 리스트를 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnsArticleomments() {
        //given
        Long articleId = 1L;
        given(articleRepository.findById(articleId)).willReturn(Optional.of(Article.of("title", "content", "#java")));

        //when
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(articleId); //제목, 본문, ID, 닉네임, 해시태그

        //then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }

    @DisplayName("댓글 정보를 입력하면 댓글을 저장한다.")
    @Test
    void givenArticleCommentInfo_whenSavingArticleComment_thenSavesArticleComment() {
        //given
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        //when
        sut.saveArticleComment(ArticleCommentDto.of(LocalDateTime.now(), "shin", LocalDateTime.now(), "shin", "content"));

        //then
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }
}