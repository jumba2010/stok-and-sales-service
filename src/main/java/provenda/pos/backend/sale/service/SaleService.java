package provenda.pos.backend.sale.service;

import provenda.pos.backend.sale.entity.SaleEntity;
import provenda.pos.backend.security.UserContext;

public interface SaleService {
	
	SaleEntity createSale(UserContext userContext, SaleEntity sale);
	
	SaleEntity cancelSale(UserContext userContext, SaleEntity sale);
	
}
