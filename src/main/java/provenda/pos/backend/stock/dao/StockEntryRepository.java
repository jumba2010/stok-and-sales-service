package provenda.pos.backend.stock.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import provenda.pos.backend.generic.dao.AbstractBaseRepository;
import provenda.pos.backend.stock.entity.StockEntry;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface StockEntryRepository extends AbstractBaseRepository<StockEntry, Long>
        , JpaSpecificationExecutor<StockEntry>{
    @Query("SELECT se FROM StockEntry se WHERE se.warehouseId = :warehouseId")
    List<StockEntry> findByWarehouseId(@Param("warehouseId") Long warehouseId);

    @Query("SELECT se FROM StockEntry se " +
            "WHERE (:warehouseId IS NULL OR se.warehouseId = :warehouseId) " +
            "AND (:productId IS NULL OR se.productId = :productId) " +
            "AND (:supplierId IS NULL OR se.supplierId = :supplierId) " +
            "AND (:startDate IS NULL OR se.receivedDate >= :startDate) " +
            "AND (:endDate IS NULL OR se.receivedDate <= :endDate)")
    List<StockEntry> findStockHistory(
            @Param("warehouseId") Long warehouseId,
            @Param("productId") Long productId,
            @Param("supplierId") Long supplierId,
            @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate
    );
}

