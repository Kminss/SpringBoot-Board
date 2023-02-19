package com.study.projectboard.service;

import com.study.projectboard.domain.Article;
import com.study.projectboard.domain.ArticleComment;
import com.study.projectboard.dto.ArticleCommentDto;
import com.study.projectboard.repository.ArticleCommentRepository;
import com.study.projectboard.repository.ArticleRepository;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {
    @InjectMocks
    private ArticleCommentService sut;
    @Mock private ArticleCommentRepository articleCommentRepository;
    @Mock private ArticleRepository articleRepository;
    @Disabled
    @DisplayName("댓글 ID를 입력하면, 해당하는 댓글리스트틀 반환한다.")
    @Test
    void givenArticleId_whenSearchingComments_thenReturnsComments() {
        //Given
        Long articleId = 1L;

        given(articleRepository.findById(articleId)).willReturn(
                Optional.of(Article.of("title", "content", "test")));

        //When
        List<ArticleCommentDto> articleComments = sut.searchArticleComment(1L);

        //Then
        assertThat(articleComments).isNotNull();
        then(articleRepository).should().findById(articleId);
    }
    @Disabled
    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenCommentInfo_whenSavingComment_thenSaveComment() {
        //Given
        given(articleCommentRepository.save(any(ArticleComment.class))).willReturn(null);

        //When
        sut.saveArticleComment(
                ArticleCommentDto.of(LocalDateTime.now(), "Mins", LocalDateTime.now(), "댓글입니다."));

        //Then
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }
}