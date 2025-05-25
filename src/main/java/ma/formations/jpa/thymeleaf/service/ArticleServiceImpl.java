package ma.formations.jpa.thymeleaf.service;

import ma.formations.jpa.thymeleaf.dtos.ArticleDto;
import ma.formations.jpa.thymeleaf.entity.Article;
import ma.formations.jpa.thymeleaf.repository.ArticleRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ArticleServiceImpl implements IArticleService {

    private final ArticleRepository articleRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ArticleDto> findAll() {
        return articleRepository.findAll()
                .stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ArticleDto> findByDescriptionContainingIgnoreCase(String keyword) {
        return articleRepository.findByDescriptionContainingIgnoreCase(keyword)
                .stream()
                .map(article -> modelMapper.map(article, ArticleDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public ArticleDto save(ArticleDto articleDto) {
        Article article = modelMapper.map(articleDto, Article.class);
        Article saved = articleRepository.save(article);
        return modelMapper.map(saved, ArticleDto.class);
    }

    @Override
    public ArticleDto findById(Long id) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("No article with ID [" + id + "] exists!"));
        return modelMapper.map(article, ArticleDto.class);
    }

    @Override
    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}