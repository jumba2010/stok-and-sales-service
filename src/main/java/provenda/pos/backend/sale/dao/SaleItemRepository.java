package provenda.pos.backend.sale.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import provenda.pos.backend.generic.dao.AbstractBaseRepository;
import provenda.pos.backend.sale.entity.SaleEntity;

import java.util.List;

@Repository
public interface SaleItemRepository extends AbstractBaseRepository<SaleEntity, Long> {

	@Query("Select s from SaleEntity s INNER JOIN FETCH s.items i INNER JOIN FETCH i.product WHERE s.status =:status AND s.sucursalId = :sucursalId AND s.active = :active and s.state =:state")
    List<SaleEntity> findSalesByStatusAndSucursalIdAndActiveAndState(@Param("status") String status,
                                                                     @Param("sucursalId") Long sucursalId, @Param("active") boolean active, @Param("state") int state);
}
