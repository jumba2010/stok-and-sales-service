package provenda.pos.backend.stock.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import provenda.pos.backend.exceptions.BusinessException;
import provenda.pos.backend.product.dao.ProductRepository;
import provenda.pos.backend.product.entity.ProductEntity;
import provenda.pos.backend.product.service.ProductService;
import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.stock.dao.StockRepository;
import provenda.pos.backend.stock.entity.ProductStock;
import provenda.pos.backend.stock.entity.StockEntity;

/**
 * @author Judiao Mbaua
 *
 *         <p>
 *         Decrements stock for a product using <b>FIFO</b> (first-in, first-out)
 *         consumption: the oldest stock batches of <i>that product</i> are drained
 *         first, so the cost of goods sold reflects the purchase price of the
 *         batches actually consumed.
 *         </p>
 *
 *         <p>
 *         The whole operation is atomic: if the product does not have enough
 *         available quantity the request is rejected before anything is changed,
 *         and the product total and the per-batch quantities are updated in the
 *         same transaction so they can never drift apart.
 *         </p>
 */
@Component
@RequiredArgsConstructor
public class StockRemoval implements StockUpdateType {

	private final ProductService productService;

	private final ProductRepository productRepo;

	private final StockRepository stockRepository;

	@Override
	@Transactional(rollbackFor = BusinessException.class)
	public void updateStock(UserContext userContext, ProductStock productStock) throws BusinessException {
		int requested = productStock.getQuantity();
		if (requested <= 0) {
			throw new BusinessException("stock.invalid.quantity", "Quantity to remove must be greater than zero");
		}

		ProductEntity product = productRepo.findById(productStock.getProductId())
				.orElseThrow(() -> new BusinessException("product.not.found", "Product not found"));

		// Only this product's batches, oldest first
		List<StockEntity> batches = stockRepository
				.findByProductIdAndAvailableQuanityGreaterThanOrderById(product.getId(), 0);

		int available = batches.stream().mapToInt(StockEntity::getAvailableQuanity).sum();
		if (available < requested) {
			throw new BusinessException("stock.insufficient",
					"Insufficient stock for product " + product.getId() + ": requested " + requested
							+ ", available " + available);
		}

		int remaining = requested;
		for (StockEntity batch : batches) {
			if (remaining == 0) {
				break;
			}
			int consumed = Math.min(batch.getAvailableQuanity(), remaining);
			batch.setAvailableQuanity(batch.getAvailableQuanity() - consumed);
			remaining -= consumed;
		}
		stockRepository.saveAll(batches);

		if (productStock.getSellPrice() != null) {
			product.setCurrentPrice(productStock.getSellPrice());
		}
		product.setAvailableQuantity(product.getAvailableQuantity() - requested);
		productService.updateProduct(userContext, product.getId(), product);
	}
}
