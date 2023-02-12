package com.study.projectboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
/articles/search	GET	게시판 검색 전용 페이지
/articles/search-hashtag	GET	게시판 해시태그 검색 전용 페이지
*/
@RequestMapping("/articles")
@Controller
public class ArticleController {
    @GetMapping
    public String articles(ModelMap map) {
        map.addAttribute("articles", List.of());
        return "articles/index";
    }

    @GetMapping("/{articleId}")
    public String article(@PathVariable Long articleId, ModelMap map) {
        map.addAttribute("article", "article"); //TODO: 실제 Article DTO 구현해야함
        map.addAttribute("articleComments", List.of());

        return "articles/detail";
    }
}
