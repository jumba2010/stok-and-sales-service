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
import provenda.pos.backend.stock.entity.StockType;

/**
 * @author Judiao Mbaua
 *
 *         <p>
 *         This class will be responsible for caring all the logic about
 *         incrementing the Stock
 *         </p>
 */
@Component
@AllArgsConstructor
public class StockPlacement implements StockUpdateType {

	@Autowired
	private ProductService productService;

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private StockRepository stockRepository;

	@Override
	public void updateStock(UserContext userContext, ProductStock productStock) throws BusinessException {
		// Create a new stock entry
		StockEntity stock = new StockEntity();
		stock.setStockType(StockType.ENTRANCE.getType());
		stock.setProductId(productStock.getProductId());
		stock.setAvailableQuanity(productStock.getQuantity());
		stock.setSellPrice(productStock.getSellPrice());
		stock.setPurchasePrice(productStock.getPurchasePrice());
		stock.setQuantity(productStock.getQuantity());
		stockRepository.save(stock);

		// Update the product stock
		productRepo.findById(productStock.getProductId()).ifPresent(p -> {
			p.setCurrentPrice(productStock.getSellPrice());
			p.setAvailableQuantity(p.getAvailableQuantity() + productStock.getQuantity()); // Increment stock

			try {
				// Call updateProduct with productId and the updated ProductEntity
				productService.updateProduct(userContext, p.getId(), p);
			} catch (BusinessException e) {
				throw new RuntimeException(e);
			}
		});
	}


}