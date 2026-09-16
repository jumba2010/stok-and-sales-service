package provenda.pos.backend.stock.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import provenda.pos.backend.generic.entity.LifeCycleEntity;
import provenda.pos.backend.user.entity.SucursalEntity;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "inventory")
public class InventoryEntity extends LifeCycleEntity<Long> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
    private SucursalEntity sucursal;

    @Column(name = "warehouse_id", nullable = false)
    private Long warehouseId;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<InventoryAdjustmentEntity> adjustments;

    @Column(name = "status", nullable = false)
    private int status;

}
