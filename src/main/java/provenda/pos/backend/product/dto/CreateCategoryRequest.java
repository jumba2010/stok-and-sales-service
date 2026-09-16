package provenda.pos.backend.product.dto;

import lombok.Data;
import provenda.pos.backend.product.entity.CategoryEntity;

@Data
public class CreateCategoryRequest {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String lang;

    public static CategoryEntity getCategory(CreateCategoryRequest request) {
        if (request == null) return null;

        return CategoryEntity.builder()
                .id(request.getId())
                .code(request.getCode())
                .name(request.getName())
                .description(request.getDescription())
                .lang(request.getLang())
                .build();
    }

    public static CreateCategoryRequest fromEntity(CategoryEntity category) {
        if (category == null) return null;

        CreateCategoryRequest request = new CreateCategoryRequest();
        request.setId(category.getId());
        request.setCode(category.getCode());
        request.setName(category.getName());
        request.setDescription(category.getDescription());
        request.setLang(category.getLang());

        return request;
    }
}

