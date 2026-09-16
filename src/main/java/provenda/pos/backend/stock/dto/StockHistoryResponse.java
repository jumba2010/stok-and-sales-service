package provenda.pos.backend.stock.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StockHistoryResponse {
    private Long productId;
    private String productName;
    //private String lot;
    private int receivedQuantity;
    //private double purchasePrice;
    private Long supplierId;
    private String receivedDate;
    private String notes;
}
