package com.study.projectboard.service;

import com.study.projectboard.domain.Article;
import com.study.projectboard.domain.ArticleComment;
import com.study.projectboard.domain.UserAccount;
import com.study.projectboard.dto.ArticleCommentDto;
import com.study.projectboard.dto.UserAccountDto;
import com.study.projectboard.repository.ArticleCommentRepository;
import com.study.projectboard.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 댓글")
@ExtendWith(MockitoExtension.class)
class ArticleCommentServiceTest {
    @InjectMocks
    private ArticleCommentService sut;
    @Mock
    private ArticleCommentRepository articleCommentRepository;
    @Mock
    private ArticleRepository articleRepository;

    @DisplayName("게시글 ID로 조회하면, 해당하는 댓글리스트틀 반환한다.")
    @Test
    void givenArticleId_whenSearchingArticleComments_thenReturnsComments() {
        //Given
        Long articleId = 1L;

        ArticleComment expected = createArticleComment("content");
        given(articleCommentRepository.findByArticle_Id(articleId)).willReturn(List.of(expected));

        //When
        List<ArticleCommentDto> actual = sut.searchArticleComment(articleId);

        //Then
        assertThat(actual)
                .isNotNull()
                .hasSize(1)
                .first().hasFieldOrPropertyWithValue("content", expected.getContent());
        then(articleCommentRepository).should().findByArticle_Id(articleId);
    }
    @Disabled
    @DisplayName("댓글 정보를 입력하면, 댓글을 저장한다.")
    @Test
    void givenCommentInfo_whenSavingComment_thenSaveComment() {
        //Given
        ArticleCommentDto dto = createArticleCommentDto("댓글");
        given(articleRepository.getReferenceById(dto.articleId())).willReturn(null);

        //When
        sut.saveArticleComment(dto);


        //Then
        then(articleRepository).should().getReferenceById(dto.articleId());
        then(articleCommentRepository).should().save(any(ArticleComment.class));
    }

    @Disabled
    @DisplayName("댓글 저장 시 맞는 게시글이 없으면, 경고 로그를 찍고 아무것도 안한다.")
    @Test
    void givenNoexistentArticle_whenSavingArticleComment_thenLogsSituationAndDoesNothing() {
        //Given
        ArticleCommentDto dto = createArticleCommentDto("댓글");
        given(articleRepository.getReferenceById(dto.articleId())).willThrow(EntityNotFoundException.class);

        //When
        sut.saveArticleComment(dto);

        //Then
        then(articleRepository).should().getReferenceById(dto.articleId());
        then(articleCommentRepository).shouldHaveNoInteractions();
    }

    @Disabled
    @DisplayName("댓글 정보를 입력하면, 댓글을 수정한다.")
    @Test
    void givenArticleCommentInfo_whenUpdatingArticleComment_thenUpdateArticleComment() {
        //Given
        String oldContent = "content";
        String updateContent = "댓글";
        ArticleComment articleComment = createArticleComment(oldContent);
        ArticleCommentDto dto = createArticleCommentDto(updateContent);

        given(articleCommentRepository.getReferenceById(dto.id())).willReturn(articleComment);

        //When
        sut.updateArticleComment(dto);

        //Then
        assertThat(articleComment.getContent())
                .isNotEqualTo(oldContent)
                .isEqualTo(updateContent);
        then(articleCommentRepository).should().getReferenceById(dto.id());
    }

    @Disabled
    @DisplayName("없는 댓글 정보를 수정할 시, 경고 로그를 찍고 아무 것도 안 한다.")
    @Test
    void givenNoexistenArticleCommen_whenUpdatingArticleComment_thenLogsWarningAndDoesNothing() {
        //Given
        ArticleCommentDto dto = createArticleCommentDto("댓글");
        given(articleCommentRepository.getReferenceById(dto.id())).willThrow(EntityNotFoundException.class);

        //When
        sut.updateArticleComment(dto);

        //Then
        then(articleCommentRepository).should().getReferenceById(dto.id());
    }

    @Disabled
    @DisplayName("댓글 ID를 입력하면, 댓글을 삭제한다.")
    @Test
    void givenArticleCommentId_whenDeletingArticleComment_thenDeleteArticleComment() {
        //Given
        Long articleCommentId = 1L;

        willDoNothing().given(articleCommentRepository).deleteById(articleCommentId);

        //When
        sut.deleteArticleComment(articleCommentId);

        //Then
        then(articleCommentRepository).should().deleteById(articleCommentId);

    }

    private ArticleCommentDto createArticleCommentDto(String content) {
        return ArticleCommentDto.of(
                1L,
                1L,
                createUserAccountDto(),
                content,
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }

    private UserAccountDto createUserAccountDto() {
        return UserAccountDto.of(
                "uno",
                "password",
                "uno@mail.com",
                "Uno",
                "This is memo",
                LocalDateTime.now(),
                "uno",
                LocalDateTime.now(),
                "uno"
        );
    }

    private ArticleComment createArticleComment(String content) {
        return ArticleComment.of(
                Article.of(createUserAccount(), "title", "content", "hashtag"),
                createUserAccount(),
                content
        );
    }

    private UserAccount createUserAccount() {
        return UserAccount.of(
                "uno",
                "password",
                "uno@email.com",
                "Uno",
                null
        );
    }
}