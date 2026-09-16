package provenda.pos.backend.sale.service;

import provenda.pos.backend.sale.entity.SaleEntity;
import provenda.pos.backend.security.UserContext;

import java.util.List;

public interface SaleQueryService {
	
	List<SaleEntity> findSalesByStatusAndSucursalIdAndActiveAndState(UserContext userContext, String status,
                                                                     Long sucursalId);
}
