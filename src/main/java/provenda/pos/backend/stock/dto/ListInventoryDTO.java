package provenda.pos.backend.stock.dto;

import lombok.Builder;
import lombok.Data;
import provenda.pos.backend.enums.InventoryStatus;
import provenda.pos.backend.stock.entity.InventoryEntity;

@Data
@Builder
public class ListInventoryDTO {
    private Long inventoryId;
    private String date;
    private Long warehouse;
    private Long responsibleUser;
    private int totalProducts;
    private String status;

    public static ListInventoryDTO toListInventoryDTO(InventoryEntity inventoryEntity) {
        return ListInventoryDTO.builder()
                .inventoryId(inventoryEntity.getId())
                .date(inventoryEntity.getCreatedAt().toString())
                .warehouse(inventoryEntity.getWarehouseId())
                .responsibleUser(inventoryEntity.getCreatedBy())
                .totalProducts(inventoryEntity.getAdjustments() != null ? inventoryEntity.getAdjustments().size() : 0)
                .status(inventoryEntity.getStatus() == InventoryStatus.PENDING.getCode() ? "PENDING" : "FINISHED")
                .build();
    }
}

