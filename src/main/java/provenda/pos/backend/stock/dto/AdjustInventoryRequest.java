package provenda.pos.backend.stock.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AdjustInventoryRequest {
    @NotNull
    private Long inventoryId;

    @NotNull
    private Long productId;

    @NotNull
    private int physicalQuantity;

    private String reason;

}