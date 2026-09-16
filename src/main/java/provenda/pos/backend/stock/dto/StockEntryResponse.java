package provenda.pos.backend.stock.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class StockEntryResponse {
    private Long id;
    private String receivedDate;
    private String reference;
    private Long warehouseId;
    private Long supplierId;
    private Long productId;


}
