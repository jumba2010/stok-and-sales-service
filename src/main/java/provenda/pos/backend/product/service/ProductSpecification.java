package provenda.pos.backend.product.service;

import org.springframework.data.jpa.domain.Specification;
import provenda.pos.backend.product.dto.SearchProductRequest;
import provenda.pos.backend.product.entity.ProductEntity;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {

    public static Specification<ProductEntity> buildDynamicQuery(SearchProductRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            predicates.add(criteriaBuilder.equal(root.get("active"), request.isActive()));

            if (request.getName() != null && !request.getName().isEmpty()) {
                predicates.add(criteriaBuilder.like(criteriaBuilder.lower(root.get("name")), "%" + request.getName().toLowerCase() + "%"));
            }

            if (request.getCode() != null && !request.getCode().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("code"), request.getCode()));
            }

            if (request.getCategoryId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("categoryId"), request.getCategoryId()));
            }

            if (request.getSupplierId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("supplierId"), request.getSupplierId()));
            }

            if (request.getSucursalId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("sucursalId"), request.getSucursalId()));
            }

            if (request.getMinPrice() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("salePrice"), request.getMinPrice()));
            }

            if (request.getMaxPrice() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("salePrice"), request.getMaxPrice()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
