package provenda.pos.backend.sale.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import provenda.pos.backend.generic.entity.LifeCyCleState;
import provenda.pos.backend.sale.dao.SaleRepository;
import provenda.pos.backend.sale.entity.SaleEntity;
import provenda.pos.backend.security.UserContext;

import java.util.List;

@Service
public class SaleQueryServiceImpl implements SaleQueryService {

	@Autowired
	private SaleRepository saleRepo;

	@Override
	public List<SaleEntity> findSalesByStatusAndSucursalIdAndActiveAndState(UserContext userContext, String status,
																			Long sucursalId) {
		
		return saleRepo.findSalesByStatusAndSucursalIdAndActiveAndState(status, sucursalId,
				LifeCyCleState.ACTIVE.isActive(), LifeCyCleState.ACTIVE.getState());
	}

}
