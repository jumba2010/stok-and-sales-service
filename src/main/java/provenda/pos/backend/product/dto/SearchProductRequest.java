package provenda.pos.backend.product.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class SearchProductRequest {
    private boolean active;
    private String name;
    private String code;
    private String sucursalId;
    private Long categoryId;
    private Long supplierId;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
}