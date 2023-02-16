package com.study.projectboard.service;

import com.study.projectboard.domain.type.SearchType;
import com.study.projectboard.dto.ArticleDto;
import com.study.projectboard.dto.ArticleUpdateDto;
import com.study.projectboard.repository.ArticleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@RequiredArgsConstructor
@Transactional
@Service
public class ArticleService {
    private final ArticleRepository articleRepository;

    @Transactional(readOnly = true)
    public Page<ArticleDto> searchArticles(SearchType title, String keyword) {
        return Page.empty();
    }

    public ArticleDto searchArticle(long l) {
        return new ArticleDto(LocalDateTime.now(),"Mins", "title","content","java");
    }

    public void saveArticle(ArticleDto dto) {
    }

    public void updateArticle(long id, ArticleUpdateDto dto) {
    }

    public void deleteArticle(long id) {
    }
}
