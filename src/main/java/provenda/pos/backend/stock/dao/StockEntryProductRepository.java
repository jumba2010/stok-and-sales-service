package provenda.pos.backend.stock.dao;

import org.springframework.stereotype.Repository;
import provenda.pos.backend.generic.dao.AbstractBaseRepository;
import provenda.pos.backend.stock.entity.StockEntryProduct;

@Repository
public interface StockEntryProductRepository extends AbstractBaseRepository<StockEntryProduct, Long> {}

