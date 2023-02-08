package com.study.projectboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
/articles/search	GET	게시판 검색 전용 페이지
/articles/search-hashtag	GET	게시판 해시태그 검색 전용 페이지
*/
@RequestMapping("/articles")
@Controller
public class ArticleController {

}
