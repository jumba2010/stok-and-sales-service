package provenda.pos.backend.stock.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import provenda.pos.backend.exceptions.BusinessException;
import provenda.pos.backend.product.dao.ProductRepository;
import provenda.pos.backend.product.entity.ProductEntity;
import provenda.pos.backend.product.service.ProductService;
import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.stock.dao.StockRepository;
import provenda.pos.backend.stock.entity.ProductStock;
import provenda.pos.backend.stock.entity.StockEntity;

@ExtendWith(MockitoExtension.class)
class StockRemovalTest {

	private static final Long PRODUCT_ID = 7L;

	@Mock
	private ProductService productService;

	@Mock
	private ProductRepository productRepository;

	@Mock
	private StockRepository stockRepository;

	@InjectMocks
	private StockRemoval stockRemoval;

	private final UserContext userContext = UserContext.getDefaultContext();

	private ProductEntity product;

	@BeforeEach
	void setUp() {
		product = new ProductEntity();
		product.setId(PRODUCT_ID);
		product.setAvailableQuantity(15);
	}

	@Test
	@DisplayName("Consumes the oldest batches first and spills over into the next one")
	void consumesBatchesInFifoOrder() throws BusinessException {
		StockEntity oldest = batch(1L, 4);
		StockEntity middle = batch(2L, 5);
		StockEntity newest = batch(3L, 6);
		when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
		when(stockRepository.findByProductIdAndAvailableQuanityGreaterThanOrderById(PRODUCT_ID, 0))
				.thenReturn(List.of(oldest, middle, newest));

		stockRemoval.updateStock(userContext, request(7));

		assertThat(oldest.getAvailableQuanity()).isZero();
		assertThat(middle.getAvailableQuanity()).isEqualTo(2);
		assertThat(newest.getAvailableQuanity()).isEqualTo(6);
		assertThat(product.getAvailableQuantity()).isEqualTo(8);
		verify(stockRepository).saveAll(List.of(oldest, middle, newest));
		verify(productService).updateProduct(userContext, PRODUCT_ID, product);
	}

	@Test
	void rejectsRemovalWhenStockIsInsufficientAndChangesNothing() throws BusinessException {
		StockEntity only = batch(1L, 3);
		when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.of(product));
		when(stockRepository.findByProductIdAndAvailableQuanityGreaterThanOrderById(PRODUCT_ID, 0))
				.thenReturn(List.of(only));

		assertThatThrownBy(() -> stockRemoval.updateStock(userContext, request(5)))
				.isInstanceOf(BusinessException.class)
				.extracting("code").isEqualTo("stock.insufficient");

		assertThat(only.getAvailableQuanity()).isEqualTo(3);
		verify(stockRepository, never()).saveAll(anyList());
		verify(productService, never()).updateProduct(any(), eq(PRODUCT_ID), any());
	}

	@Test
	void rejectsNonPositiveQuantity() {
		assertThatThrownBy(() -> stockRemoval.updateStock(userContext, request(0)))
				.isInstanceOf(BusinessException.class)
				.extracting("code").isEqualTo("stock.invalid.quantity");
	}

	@Test
	void rejectsUnknownProduct() {
		when(productRepository.findById(PRODUCT_ID)).thenReturn(Optional.empty());

		assertThatThrownBy(() -> stockRemoval.updateStock(userContext, request(1)))
				.isInstanceOf(BusinessException.class)
				.extracting("code").isEqualTo("product.not.found");
	}

	private static ProductStock request(int quantity) {
		ProductStock productStock = new ProductStock();
		productStock.setProductId(PRODUCT_ID);
		productStock.setQuantity(quantity);
		productStock.setSellPrice(new BigDecimal("12.50"));
		return productStock;
	}

	private static StockEntity batch(Long id, int available) {
		StockEntity stock = new StockEntity();
		stock.setId(id);
		stock.setProductId(PRODUCT_ID);
		stock.setAvailableQuanity(available);
		stock.setQuantity(available);
		return stock;
	}
}
