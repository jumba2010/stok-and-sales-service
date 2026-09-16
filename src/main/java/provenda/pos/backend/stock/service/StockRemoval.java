package provenda.pos.backend.stock.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import provenda.pos.backend.exceptions.BusinessException;
import provenda.pos.backend.product.dao.ProductRepository;
import provenda.pos.backend.product.service.ProductService;
import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.stock.dao.StockRepository;
import provenda.pos.backend.stock.entity.ProductStock;
import provenda.pos.backend.stock.entity.StockEntity;

import java.util.List;

/**
 * @author Judiao Mbaua
 *
 *         <p>
 *         This class will be responsible for caring all the logic about
 *         decrementing the Stock
 *         </p>
 */
@Component
@AllArgsConstructor
public class StockRemoval implements StockUpdateType {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private StockRepository StockRepo;

	@Override
	public void updateStock(UserContext userContext, ProductStock productStock) {

		// Fetch all stocks with available quantity greater than 0
		List<StockEntity> stocks = StockRepo.findByAvailableQuanityGreaterThanOrderById(0);

		// Update the product's price and total available quantity
		productRepo.findById(productStock.getProductId()).ifPresent(p -> {
			p.setCurrentPrice(productStock.getSellPrice());
			p.setAvailableQuantity(p.getAvailableQuantity() - productStock.getQuantity()); // Decrement stock

			try {
				// Call updateProduct with productId and updated ProductEntity
				productService.updateProduct(userContext, p.getId(), p);
			} catch (BusinessException e) {
				throw new RuntimeException(e);
			}
		});

		// Decrement stock quantities across multiple stock entities
		stocks.forEach(stock -> {
			if (productStock.getQuantity() > 0) { // Continue until the quantity to decrement is zero
				int difference = stock.getAvailableQuanity() - productStock.getQuantity();

				if (difference < 0) {
					// Fully consume this stock
					productStock.setQuantity(productStock.getQuantity() - stock.getAvailableQuanity());
					stock.setAvailableQuanity(0);
				} else {
					// Partially consume this stock
					stock.setAvailableQuanity(difference);
					productStock.setQuantity(0);
				}


				//stockRepository.save(stock);
			}
		});
	}


}