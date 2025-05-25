package ma.formations.jpa.thymeleaf.service;

import ma.formations.jpa.thymeleaf.dtos.ArticleDto;

import java.util.List;

public interface IArticleService {
    List<ArticleDto> findAll();

    List<ArticleDto> findByDescriptionContainingIgnoreCase(String keyword);

    ArticleDto save(ArticleDto articleDto);

    ArticleDto findById(Long id);

    void deleteById(Long id);
}