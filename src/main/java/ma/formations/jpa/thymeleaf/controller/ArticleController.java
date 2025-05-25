package ma.formations.jpa.thymeleaf.controller;

import ma.formations.jpa.thymeleaf.dtos.ArticleDto;
import ma.formations.jpa.thymeleaf.service.IArticleService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;

@Controller
@AllArgsConstructor
public class ArticleController {

    private final IArticleService articleService;

    @GetMapping(value = {"/", "/articles"})
    public String showArticleList(Model model, @RequestParam(required = false) String keyword) {
        try {
            if (keyword != null) {
                model.addAttribute("articles", articleService.findByDescriptionContainingIgnoreCase(keyword));
            } else {
                model.addAttribute("articles", articleService.findAll());
            }
            model.addAttribute("keyword", keyword);
        } catch (Exception e) {
            model.addAttribute("errorMessage", e.getMessage());
        }
        return "articles";
    }

    @GetMapping("/articles/new")
    public String showCreateForm(Model model) {
        ArticleDto article = new ArticleDto();
        model.addAttribute("article", article);
        model.addAttribute("pageTitle", "Create New Article");
        return "article_form";
    }

    @PostMapping("/articles/save")
    public String saveArticle(@ModelAttribute("article") ArticleDto articleDto, RedirectAttributes ra) {
        articleService.save(articleDto);
        ra.addFlashAttribute("message", "The article has been saved successfully!");
        return "redirect:/articles";
    }

    @GetMapping("/articles/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes ra) {
        try {
            ArticleDto article = articleService.findById(id);
            model.addAttribute("article", article);
            model.addAttribute("pageTitle", "Edit Article (ID: " + id + ")");
            return "article_form";
        } catch (Exception e) {
            ra.addFlashAttribute("message", e.getMessage());
            return "redirect:/articles";
        }
    }
}
