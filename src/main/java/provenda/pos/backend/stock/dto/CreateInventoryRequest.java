package provenda.pos.backend.stock.dto;

import lombok.Data;
import provenda.pos.backend.stock.entity.InventoryEntity;

import javax.validation.constraints.NotNull;

@Data
public class CreateInventoryRequest {

    @NotNull
    private Long warehouseId;

    private int status;

    public static InventoryEntity getInventoryInstance(CreateInventoryRequest createInventoryRequest) {
        return InventoryEntity.builder()
                .warehouseId(createInventoryRequest.getWarehouseId())
                .status(createInventoryRequest.getStatus())
                .build();
    }
}
