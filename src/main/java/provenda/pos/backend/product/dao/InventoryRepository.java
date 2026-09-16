package provenda.pos.backend.product.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import provenda.pos.backend.generic.dao.AbstractBaseRepository;
import provenda.pos.backend.stock.entity.InventoryEntity;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface InventoryRepository extends AbstractBaseRepository<InventoryEntity, Long> {
    @Query("SELECT i FROM InventoryEntity i WHERE " +
            "(:startDate IS NULL OR i.createdAt >= :startDate) AND " +
            "(:endDate IS NULL OR i.createdAt <= :endDate) AND " +
            "(:warehouseId IS NULL OR i.warehouseId = :warehouseId) AND " +
            "(:status IS NULL OR i.state = :status)")
    Page<InventoryEntity> findInventories(
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate,
            @Param("warehouseId") Long warehouseId,
            @Param("status") Integer status,
            Pageable pageable);

    @Query("SELECT i FROM InventoryEntity i " +
            "WHERE (i.createdAt >= :startDate) " +
            "AND (i.createdAt <= :endDate) " +
            "AND (i.warehouseId = :warehouseId) " +
            "AND (i.status = :status)")
    List<InventoryEntity> findByFilter(@Param("startDate") LocalDateTime startDate,
                                       @Param("endDate") LocalDateTime endDate,
                                       @Param("warehouseId") Long warehouseId,
                                       @Param("status") Integer status);

}
