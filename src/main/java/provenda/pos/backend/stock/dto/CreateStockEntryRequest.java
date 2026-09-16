package provenda.pos.backend.stock.dto;

import lombok.Data;
import provenda.pos.backend.stock.entity.StockEntry;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class CreateStockEntryRequest {
    @NotNull
    private Long warehouseId;

    private Long supplierId;

    private String reference;

    @NotNull
    private String receivedDate;

    private String notes;

    @NotNull
    private Long productId;

    @NotNull
    private int receivedQuantity;


    public static StockEntry getStockEntryInstance(CreateStockEntryRequest createStockEntryRequest) {
        LocalDate date = LocalDate.parse(createStockEntryRequest.getReceivedDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        return StockEntry.builder()
                .warehouseId(createStockEntryRequest.getWarehouseId())
                .supplierId(createStockEntryRequest.getSupplierId())
                .reference(createStockEntryRequest.getReference())
                .receivedDate(date.atStartOfDay())
                .notes(createStockEntryRequest.getNotes())
                .productId(createStockEntryRequest.getProductId())
                .receivedQuantity(createStockEntryRequest.getReceivedQuantity())
                .build();
    }
}

