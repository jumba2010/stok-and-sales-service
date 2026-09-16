package provenda.pos.backend.stock.dto;

import lombok.Data;

@Data
public class FilterInventoryRequest {

    private String startDate;
    private String endDate;
    private Long warehouseId;
    private String status;
}
