package provenda.pos.backend.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import provenda.pos.backend.product.dto.SearchProductRequest;
import provenda.pos.backend.product.entity.ProductEntity;
import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.stock.entity.StockEntity;

import java.util.List;
import java.util.Optional;

public interface ProductQueryService {

Optional<ProductEntity> findProductByCode(UserContext userContext, String ProductCode);

List<StockEntity> findStockBySucursalIdAndActiveAndState(UserContext userContext, Long SucursalId);

Page<ProductEntity> searchProducts(SearchProductRequest request, Pageable pageable);
}
