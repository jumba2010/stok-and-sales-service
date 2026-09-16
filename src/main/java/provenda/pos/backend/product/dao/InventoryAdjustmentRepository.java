package provenda.pos.backend.product.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import provenda.pos.backend.generic.dao.AbstractBaseRepository;
import provenda.pos.backend.stock.entity.InventoryAdjustmentEntity;

import java.util.List;

@Repository
public interface InventoryAdjustmentRepository extends AbstractBaseRepository<InventoryAdjustmentEntity, Long> {
    @Query("SELECT ia FROM InventoryAdjustmentEntity ia WHERE ia.inventory.id = :inventoryId")
    List<InventoryAdjustmentEntity> findByInventoryId(@Param("inventoryId") Long inventoryId);


}
