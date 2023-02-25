package com.study.projectboard.dto;

import com.study.projectboard.domain.Article;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.LinkedHashSet;

public record ArticleWithCommentsDto(Long id,
                                     UserAccountDto userAccountDto,
                                     Set<ArticleCommentDto> articleCommentsDto,
                                     String title,
                                     String content,
                                     String hashtag,
                                     LocalDateTime createdAt,
                                     String createdBy,
                                     LocalDateTime modifiedAt,
                                     String modifiedBy
) {
    public static ArticleWithCommentsDto of(Long id, UserAccountDto userAccountDto, Set<ArticleCommentDto> articleCommentsDto, String title, String content, String hashtag, LocalDateTime createdAt, String createdBy, LocalDateTime modifiedAt, String modifiedBy) {
        return new ArticleWithCommentsDto(id, userAccountDto, articleCommentsDto, title, content, hashtag, createdAt, createdBy, modifiedAt, modifiedBy);
    }

    public static ArticleWithCommentsDto from(Article entity) {
        return new ArticleWithCommentsDto(
                entity.getId(),
                UserAccountDto.from(entity.getUserAccount()),
                entity.getArticleComments().stream()
                        .map(ArticleCommentDto::from)
                        .collect(Collectors.toCollection(LinkedHashSet::new)),
                entity.getTitle(),
                entity.getContent(),
                entity.getHashtag(),
                entity.getCreatedAt(),
                entity.getCreatedBy(),
                entity.getModifiedAt(),
                entity.getModifiedBy()
        );
    }
}
