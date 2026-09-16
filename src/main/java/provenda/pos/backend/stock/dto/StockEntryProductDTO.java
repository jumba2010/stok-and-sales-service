package provenda.pos.backend.stock.dto;

import lombok.Builder;
import lombok.Data;
import provenda.pos.backend.stock.entity.StockEntryProduct;

@Data
@Builder
public class StockEntryProductDTO {
    private Long id;
    private Long productId;
    private int quantity;
    private double purchasePrice;
    private Double newSalePrice;

    public static StockEntryProductDTO fromEntity(StockEntryProduct entity) {
        return StockEntryProductDTO.builder()
                .id(entity.getId())
                .productId(entity.getId())
                .quantity(entity.getQuantity())
                .purchasePrice(entity.getPurchasePrice())
                .newSalePrice(entity.getNewSalePrice())
                .build();
    }
}
