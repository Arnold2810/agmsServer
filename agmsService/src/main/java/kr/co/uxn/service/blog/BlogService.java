package kr.co.uxn.service.blog;

import jakarta.transaction.Transactional;
import kr.co.uxn.controller.blog.dto.AddArticleRequest;
import kr.co.uxn.controller.blog.dto.UpdateArticleRequest;
import kr.co.uxn.domain.blog.Article;
import kr.co.uxn.domain.blog.Article;
import kr.co.uxn.domain.blog.repository.BlogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(int id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));
    }

    public void delete(int id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(int id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("not found : " + id));

        article.update(request.getTitle(), request.getContent());

        return article;
    }
}
