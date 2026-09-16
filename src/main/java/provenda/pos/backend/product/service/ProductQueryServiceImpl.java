package provenda.pos.backend.product.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import provenda.pos.backend.generic.entity.LifeCyCleState;
import provenda.pos.backend.product.dao.ProductRepository;
import provenda.pos.backend.product.dto.SearchProductRequest;
import provenda.pos.backend.product.entity.ProductEntity;
import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.stock.dao.StockRepository;
import provenda.pos.backend.stock.entity.StockEntity;

import java.util.List;
import java.util.Optional;

@Service
public class ProductQueryServiceImpl  implements ProductQueryService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private StockRepository stockRepo;

	@Override
	public List<StockEntity> findStockBySucursalIdAndActiveAndState(UserContext userContext, Long SucursalId) {
		return stockRepo.findStockBySucursalIdAndActiveAndState(SucursalId, LifeCyCleState.ACTIVE.isActive(),
				LifeCyCleState.ACTIVE.getState());
	}

	@Override
	public Optional<ProductEntity> findProductByCode(UserContext userContext, String ProductCode) {
		return productRepo.findByCode(ProductCode);
	}

	@Override
	public Page<ProductEntity> searchProducts(SearchProductRequest request, Pageable pageable) {
		return productRepo.findAll(
				ProductSpecification.buildDynamicQuery(request),
				pageable
		);
	}

}
