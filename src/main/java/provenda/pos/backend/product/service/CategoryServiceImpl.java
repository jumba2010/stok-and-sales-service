package provenda.pos.backend.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import provenda.pos.backend.product.dao.CategoryRepository;
import provenda.pos.backend.product.entity.CategoryEntity;

import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryEntity> getCategoriesByLang(String lang) {
        return categoryRepository.findByLang(lang);
    }

}
