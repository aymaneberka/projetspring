package ma.formations.jpa.thymeleaf.repository;

import ma.formations.jpa.thymeleaf.entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByDescriptionContainingIgnoreCase(String description);
}
