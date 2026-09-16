package provenda.pos.backend.product.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import provenda.pos.backend.exceptions.BusinessException;
import provenda.pos.backend.product.entity.ProductEntity;
import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.stock.entity.ProductStock;

public interface ProductService {

	ProductEntity createProduct(UserContext userContext, ProductEntity product) throws BusinessException;

	void updateProduct(UserContext userContext, Long productId, ProductEntity product) throws BusinessException;

	void updateStock(UserContext userContext, ProductStock productStock)throws BusinessException;

	void deleteProduct(UserContext userContext, Long productId) throws BusinessException;

}
