package com.study.projectboard.domain;

import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
@Getter
@ToString
@Table(indexes = {
        @Index(columnList = "title"),
        @Index(columnList = "hashtag"),
        @Index(columnList = "createdAt"),
        @Index(columnList = "createdBy")
})
@Entity
public class ArticleComment {
    private Long id;
    private Article article; // 게시글 (ID)
    private String content; // 본문

    private LocalDateTime createAt; // 생성일시
    private String createdBy; // 생성자
    private LocalDateTime modifiedAt; // 수정일시
    private String modifiedBy; // 수정자
}
