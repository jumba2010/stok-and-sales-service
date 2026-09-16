package provenda.pos.backend.stock.service;

import org.springframework.data.jpa.domain.Specification;
import provenda.pos.backend.stock.dto.StockHistoryFilterRequest;
import provenda.pos.backend.stock.entity.StockEntry;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

public class StockSpecification {

    public static Specification<StockEntry> buildDynamicQuery(StockHistoryFilterRequest request) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (request.getWarehouseId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("warehouseId"), request.getWarehouseId()));
            }

            if (request.getProductId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("productId"), request.getProductId()));
            }

            /*
            if (request.getLotId() != null && !request.getLotId().isEmpty()) {
                predicates.add(criteriaBuilder.equal(root.get("reference"), request.getLotId()));
            }
             */

            if (request.getStartDate() != null) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("receivedDate"), request.getStartDate()));
            }

            if (request.getEndDate() != null) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("receivedDate"), request.getEndDate()));
            }

            if (request.getSupplierId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("supplierId"), request.getSupplierId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
