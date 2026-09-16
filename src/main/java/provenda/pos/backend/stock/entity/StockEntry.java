package provenda.pos.backend.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import provenda.pos.backend.generic.entity.LifeCycleEntity;
import provenda.pos.backend.product.entity.ProductEntity;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "stock_entry")
public class StockEntry extends LifeCycleEntity<Long> {
    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;

    @Column(name = "supplier_id")
    private Long supplierId;

    @Column(name = "reference")
    private String reference;

    @Column(name = "received_date", nullable = false)
    private LocalDateTime receivedDate;

    @Column(name = "notes")
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", insertable = false, updatable = false, nullable = false)
    private ProductEntity product;

    @Column(name = "product_id")
    private Long productId;

    @Column(name = "received_quantity", nullable = false)
    private int receivedQuantity;
}

