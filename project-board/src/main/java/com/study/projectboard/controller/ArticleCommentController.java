package com.study.projectboard.controller;

import com.study.projectboard.dto.ArticleCommentDto;
import com.study.projectboard.dto.UserAccountDto;
import com.study.projectboard.dto.request.ArticleCommentRequest;
import com.study.projectboard.service.ArticleCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@RequestMapping("/comments")
@Controller
public class ArticleCommentController {
    private final ArticleCommentService articleCommentService;
    //추가, 삭제

    @PostMapping("/new")
    public String postNewArticleComment(ArticleCommentRequest articleCommentRequest) {
        // TODO: 인증 정보를 넣어줘야 한다.

        articleCommentService.saveArticleComment(articleCommentRequest.toDto(UserAccountDto.of(
                "mins",
                "pw",
                "mins@email.com",
                "minss",
                null
        )));
        return "redirect:/articles/" + articleCommentRequest.articleId();
    }


    @PostMapping("/{commentId}/delete")
    public String deleteArticleComment(@PathVariable Long commentId, String articleId) {
        // TODO: 인증 정보를 넣어줘야 한다.
        articleCommentService.deleteArticleComment(commentId);

        return "redirect:/articles/" + articleId;
    }
}
