package ma.formations.jpa.thymeleaf.controller;

import ma.formations.jpa.thymeleaf.service.IArticleService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
@AllArgsConstructor
public class ArticleRestController {

    private final IArticleService articleService;

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable Long id) {
        articleService.deleteById(id);
        return ResponseEntity.ok("Article deleted successfully");
    }
}