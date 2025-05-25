package ma.formations.jpa.thymeleaf;

import ma.formations.jpa.thymeleaf.dtos.ArticleDto;
import ma.formations.jpa.thymeleaf.service.IArticleService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MainApplication {

	public static void main(String[] args) {
		SpringApplication.run(MainApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(IArticleService articleService) {
		return args -> {
			articleService.save(ArticleDto.builder()
					.description("Notebook")
					.price(3.5)
					.quantity(20)
					.build());

			articleService.save(ArticleDto.builder()
					.description("Pen")
					.price(1.2)
					.quantity(100)
					.build());
		};
	}
}
