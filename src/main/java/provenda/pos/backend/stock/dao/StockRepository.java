package provenda.pos.backend.stock.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import provenda.pos.backend.generic.dao.AbstractBaseRepository;
import provenda.pos.backend.stock.entity.StockEntity;

import java.util.List;

@Repository
public interface StockRepository extends AbstractBaseRepository<StockEntity
, Long> {

	
List<StockEntity> findByAvailableQuanityGreaterThanOrderById(int quantity);

@Query("Select s from StockEntity s INNER JOIN FETCH s.product WHERE s.sucursalId = :sucursalId AND s.active = :active and s.state =:state")
List<StockEntity> findStockBySucursalIdAndActiveAndState(@Param("sucursalId")Long sucursalId,@Param("active") boolean active,@Param("state") int state );
}
