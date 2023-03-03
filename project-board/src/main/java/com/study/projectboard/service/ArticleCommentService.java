package com.study.projectboard.service;

import com.study.projectboard.dto.ArticleCommentDto;

import com.study.projectboard.dto.ArticleDto;
import com.study.projectboard.repository.ArticleCommentRepository;
import com.study.projectboard.repository.ArticleRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleCommentService {
    private final ArticleCommentRepository articleCommentRepository;
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public List<ArticleCommentDto> searchArticleComment(long id) {
        return articleCommentRepository.findByArticle_Id(id)
                .stream()
                .map(ArticleCommentDto::from)
                .toList();
    }

    public void saveArticleComment(ArticleCommentDto dto) {
        Optional<ArticleDto> articleDto = articleRepository.findById(dto.articleId()).map(ArticleDto::from);
        if (articleDto.isEmpty()) {
            throw new EntityNotFoundException("게시글이 없습니다. article ID: " + dto.articleId());
        }
        articleCommentRepository.save(dto.toEntity(articleDto.get().toEntity()));
    }

    public void updateArticleComment(ArticleCommentDto dto) {
    }

    public void deleteArticleComment(Long articleCommentId) {
    }
}
