package kr.co.uxn.domain.blog.repository;

import kr.co.uxn.domain.blog.Article;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogRepository extends JpaRepository<Article, Integer> {
}

