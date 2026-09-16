package provenda.pos.backend.product.dto;

import lombok.Builder;
import lombok.Data;
import provenda.pos.backend.stock.entity.InventoryAdjustmentEntity;

@Data
@Builder
public class ProductAdjustmentDTO {
    private Long productId;
    private String name;
    private int systemQuantity;
    private int physicalQuantity;
    private int adjustment;
    private String reason;

    public static ProductAdjustmentDTO toProductAdjustmentDTO(InventoryAdjustmentEntity adjustmentEntity) {
        return ProductAdjustmentDTO.builder()
                .productId(adjustmentEntity.getProduct().getId())
                .name(adjustmentEntity.getProduct().getName())
                .systemQuantity(adjustmentEntity.getSystemQuantity())
                .physicalQuantity(adjustmentEntity.getPhysicalQuantity())
                .adjustment(adjustmentEntity.getDiscrepancy())
                .reason(adjustmentEntity.getReason())
                .build();
    }
}
