package com.study.projectboard.dto;

import java.time.LocalDateTime;

public record ArticleDto(
        LocalDateTime createdAt,
        String CreatedBy,
        String title,
        String content,
        String hashtag
) {
    public static ArticleDto of(LocalDateTime createdAt, String CreatedBy, String title, String content, String hashtag) {
        return new ArticleDto(createdAt, CreatedBy, title, content, hashtag);
    }
}
