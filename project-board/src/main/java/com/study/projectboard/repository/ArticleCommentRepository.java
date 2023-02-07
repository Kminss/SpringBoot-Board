package com.study.projectboard.repository;

import com.querydsl.core.types.dsl.DateTimeExpression;
import com.querydsl.core.types.dsl.StringExpression;
import com.study.projectboard.domain.ArticleComment;
import com.study.projectboard.domain.QArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleCommentRepository extends
        JpaRepository<ArticleComment, Long>,
        QuerydslPredicateExecutor<ArticleComment>,
        QuerydslBinderCustomizer<QArticle>
{
    @Override
    default void customize(QuerydslBindings bindings, QArticle root) { //Spring data JPA를 이욯하여 사용하기때문에 인터페이스 구현체를 만들지 않음
        bindings.excludeUnlistedProperties(true);
        bindings.including(root.content, root.createdAt, root.createdBy);
        bindings.bind(root.content).first(StringExpression::containsIgnoreCase); //  like '${v}'
        bindings.bind(root.createdBy).first(StringExpression::containsIgnoreCase); //  like '${v}'
        bindings.bind(root.createdAt).first(DateTimeExpression::eq); //  like '${v}'
        //bindings.bind(root.title).first(StringExpression::likeIgnoreCase);      //like '%${V}%'
    }
}
