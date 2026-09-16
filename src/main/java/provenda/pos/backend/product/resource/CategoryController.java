package provenda.pos.backend.product.resource;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import provenda.pos.backend.product.dto.CreateCategoryRequest;
import provenda.pos.backend.product.service.CategoryService;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CreateCategoryRequest> getCategoriesByLang(@RequestParam String lang) {
        return categoryService.getCategoriesByLang(lang).stream()
                .map(CreateCategoryRequest::fromEntity)
                .collect(Collectors.toList());
    }
}
