package com.study.projectboard.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.study.projectboard.domain.Article;
import com.study.projectboard.domain.QArticle;
import com.study.projectboard.dto.ArticleDto;
import com.study.projectboard.repository.querydsl.ArticleRepositoryCustom;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends
        JpaRepository<Article, Long>,
        ArticleRepositoryCustom,
        QuerydslPredicateExecutor<Article>, //기본적인 Entity 필드의 검색기능을 지원한다. 부분검색, 대소문자구분 지원 X
        QuerydslBinderCustomizer<QArticle> //검색 등 커스텀 기능 구현
{
    Page<Article> findByTitleContaining(String title, Pageable pageable);
    Page<Article> findByContentContaining(String content, Pageable pageable);
    Page<Article> findByUserAccount_UserIdContaining(String userId, Pageable pageable);
    Page<Article> findByUserAccount_Nickname(String nickname, Pageable pageable);
    Page<Article> findByHashtag(String hashtag, Pageable pageable);

    void deleteByIdAndUserAccount_UserId(Long articleId, String userId);
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) { //Spring data JPA를 이욯하여 사용하기때문에 인터페이스 구현체를 만들지 않음
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.title, root.content, root.hashtag, root.createdAt, root.createdBy);
        bindings.bind(root.title).first(StringExpression::containsIgnoreCase); //  like '${v}'
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); //  like '${v}'
        bindings.bind(root.hashtag).first(StringExpression::containsIgnoreCase); //  like '${v}'
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); //  like '${v}'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); //  like '${v}'
        //bindings.bind(root.title).first(StringExpression::likeIgnoreCase);      //like '%${V}%'
    }
}
