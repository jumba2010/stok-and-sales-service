package provenda.pos.backend.product.service;

import provenda.pos.backend.product.entity.CategoryEntity;

import java.util.List;

public interface CategoryService {

    List<CategoryEntity> getCategoriesByLang(String lang);
}
