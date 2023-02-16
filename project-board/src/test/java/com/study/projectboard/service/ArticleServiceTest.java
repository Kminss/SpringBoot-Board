package com.study.projectboard.service;

import com.study.projectboard.domain.Article;
import com.study.projectboard.domain.type.SearchType;
import com.study.projectboard.dto.ArticleDto;
import com.study.projectboard.dto.ArticleUpdateDto;
import com.study.projectboard.repository.ArticleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 게시글")
@ExtendWith(MockitoExtension.class)
class ArticleServiceTest {
    @InjectMocks
    private ArticleService sut;
    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글을 검색하면 게시글 리스트를 반환한다.")
    @Test
    void givenSearchParameters_whenSearchingArticles_thenReturnsArticleList() {
        //Given

        //When
        Page<ArticleDto> articles = sut.searchArticles(SearchType.TITLE, "keyword"); // 제목, 본문, ID, 닉네임, 해시태그

        //Then
        assertThat(articles).isNotNull();
    }

    @DisplayName("게시글을 조회하면 게시글을 반환한다.")
    @Test
    void givenArticleId_whenSearchArticle_thenReturnsArticle() {

        //Given

        //When
        ArticleDto article = sut.searchArticle(1L); // 제목, 본문, ID, 닉네임, 해시태그

        //Then
        assertThat(article).isNotNull();
    }

    @DisplayName("게시글 정보를 입력하면, 게시글을 생성한다.")
    @Test
    void givenArticleInfo_whenSaveArticle_thenSavesArticle() {
        given(articleRepository.save(any(Article.class))).willReturn(null);
        sut.saveArticle(ArticleDto.of(LocalDateTime.now(), "Mins", "제목입니다", "본문입니다.", "Test"));

        then(articleRepository).should().save(any(Article.class));
    }
    @DisplayName("게시글 ID와 수정정보를 입력하면, 게시글을 수정한다.")
    @Test
    void givenArticleIdAndModifiedInfo_whenUpdatingArticle_thenUpdateArticle() {
        given(articleRepository.save(any(Article.class))).willReturn(null);
        sut.updateArticle(1L, ArticleUpdateDto.of("제목입니다", "본문입니다.", "Test"));

        then(articleRepository).should().save(any(Article.class));
    }

    @DisplayName("게시글 ID와 입력하면, 게시글을 삭제한다.")
    @Test
    void givenArticleId_whenDeletingArticle_thenDeleteArticle() {
        //given
        willDoNothing().given(articleRepository).delete(any(Article.class));
        //when
        sut.deleteArticle(1L);
        //then
        then(articleRepository).should().delete(any(Article.class));
    }
}