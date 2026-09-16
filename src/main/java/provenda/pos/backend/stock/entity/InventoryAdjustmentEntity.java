package provenda.pos.backend.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import provenda.pos.backend.generic.entity.LifeCycleEntity;
import provenda.pos.backend.product.entity.ProductEntity;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
@Table(name = "inventory_adjustment")
public class InventoryAdjustmentEntity extends LifeCycleEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "inventory_id", nullable = false)
    private InventoryEntity inventory;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private ProductEntity product;

    @Column(name = "system_quantity", nullable = false)
    private int systemQuantity;

    @Column(name = "physical_quantity", nullable = false)
    private int physicalQuantity;

    @Column(name = "discrepancy", nullable = false)
    private int discrepancy;

    @Column(name = "reason")
    private String reason;
}
