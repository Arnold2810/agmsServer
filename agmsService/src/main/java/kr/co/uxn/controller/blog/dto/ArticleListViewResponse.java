package kr.co.uxn.controller.blog.dto;

import kr.co.uxn.domain.blog.Article;
import lombok.Getter;

@Getter
public class ArticleListViewResponse {

    private final Integer id;
    private final String title;
    private final String content;

    public ArticleListViewResponse(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
