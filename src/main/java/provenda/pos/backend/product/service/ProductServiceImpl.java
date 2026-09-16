package provenda.pos.backend.product.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import provenda.pos.backend.exceptions.BusinessException;
import provenda.pos.backend.exceptions.ValidationException;
import provenda.pos.backend.generic.entity.LifeCyCleState;
import provenda.pos.backend.generic.service.AbstractServiceImpl;
import provenda.pos.backend.product.dao.CategoryRepository;
import provenda.pos.backend.product.dao.ProductRepository;
import provenda.pos.backend.product.entity.ProductEntity;
import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.stock.entity.ProductStock;
import provenda.pos.backend.utils.BusinessConstants;
import provenda.pos.backend.utils.ValidationUtils;

@Service
public class ProductServiceImpl extends AbstractServiceImpl<ProductEntity, Long> implements ProductService {

	private final ProductRepository productRepository;
	private final CategoryRepository categoryRepository;


	@Autowired
	public ProductServiceImpl(ProductRepository productRepository, CategoryRepository categoryRepository) {
		super(productRepository);
		this.productRepository = productRepository;
		this.categoryRepository = categoryRepository; // Properly initialize
	}

	@Override
	public ProductEntity createProduct(UserContext userContext, ProductEntity product) throws BusinessException {
		var category = categoryRepository.findById(product.getCategoryId())
				.orElseThrow(() -> new BusinessException("category.is.required", "Category is required"));

		product.setCategory(category);
		ValidationUtils.validateProductCreation(productRepository, product, categoryRepository);

		this.create(userContext, product);
		return product;
	}

	@Override
	public void updateProduct(UserContext userContext, Long productId, ProductEntity updatedProduct) throws BusinessException {
		ValidationUtils.validateProductUpdate(productRepository, categoryRepository, productId, updatedProduct);


		ProductEntity existingProduct = productRepository.findById(productId)
				.orElseThrow(() -> new ValidationException(BusinessConstants.PRODUCT_NOT_FOUND, "Product not found with ID: " + productId));

		existingProduct.setName(updatedProduct.getName());
		existingProduct.setCode(updatedProduct.getCode());
		existingProduct.setDescription(updatedProduct.getDescription());
		existingProduct.setCategory(updatedProduct.getCategory());
		existingProduct.setCurrentPrice(updatedProduct.getCurrentPrice());
		existingProduct.setAvailableQuantity(updatedProduct.getAvailableQuantity());
		existingProduct.setAlertQuantity(updatedProduct.getAlertQuantity());
		existingProduct.setUnitId(updatedProduct.getUnitId());
	}

	@Override
	public void deleteProduct(UserContext userContext, Long productId) throws BusinessException {
		ProductEntity product = productRepository.findById(productId)
				.orElseThrow(() -> new ValidationException(BusinessConstants.PRODUCT_NOT_FOUND,
						"Product not found with ID: " + productId));

		product.setActive(LifeCyCleState.DELETED.isActive());
		product.setState(LifeCyCleState.INACTIVE.getState());

		productRepository.save(product);
	}

	@Override
	public void updateStock(UserContext userContext, ProductStock productStock) {
	}

}
