package provenda.pos.backend.sale.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import provenda.pos.backend.generic.service.AbstractServiceImpl;
import provenda.pos.backend.sale.dao.SaleRepository;
import provenda.pos.backend.sale.entity.SaleEntity;
import provenda.pos.backend.sale.entity.SaleStatus;
import provenda.pos.backend.security.UserContext;

@Service
public class SaleServiceImpl extends AbstractServiceImpl<SaleEntity, Long> implements SaleService {

	@Autowired
	public SaleServiceImpl(SaleRepository saleRepository) {
		super(saleRepository);
	}

	@Override
	public SaleEntity createSale(UserContext userContext, SaleEntity sale) {
		super.create(userContext, sale);
		return sale;
	}

	@Override
	public SaleEntity cancelSale(UserContext userContext, SaleEntity sale) {
		sale.setStatus(SaleStatus.CANCELED.getStatus());
			super.update(userContext, sale);
		
		return sale;
	}




}
