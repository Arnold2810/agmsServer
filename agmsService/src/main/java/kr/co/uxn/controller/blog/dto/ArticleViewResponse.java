package kr.co.uxn.controller.blog.dto;

import kr.co.uxn.domain.blog.Article;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@NoArgsConstructor
@Getter
public class ArticleViewResponse {

  private Integer id;
  private String title;
  private String content;
  private OffsetDateTime createdAt;

  public ArticleViewResponse(Article article) {
    this.id = article.getId();
    this.title = article.getTitle();
    this.content = article.getContent();
    this.createdAt = article.getCreatedAt();
  }
}
