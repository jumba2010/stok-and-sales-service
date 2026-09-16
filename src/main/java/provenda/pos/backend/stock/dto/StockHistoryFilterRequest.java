package provenda.pos.backend.stock.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StockHistoryFilterRequest {
    private Long warehouseId;
    private Long productId;
    //private String lotId;
    private String startDate;
    private String endDate;
    private Long supplierId;
}