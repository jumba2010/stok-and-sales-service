package provenda.pos.backend.stock.service;

import provenda.pos.backend.exceptions.BusinessException;
import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.stock.entity.ProductStock;

public interface StockUpdateType {

	void updateStock(UserContext usercontext, ProductStock productStock) throws BusinessException;
}
