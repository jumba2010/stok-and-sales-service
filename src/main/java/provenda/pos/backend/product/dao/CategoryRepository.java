package provenda.pos.backend.product.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import provenda.pos.backend.product.entity.CategoryEntity;
import provenda.pos.backend.product.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryEntity, Long> {

    Optional<CategoryEntity> findByCode(final String code);

    List<CategoryEntity> findByLang(String lang);
}
