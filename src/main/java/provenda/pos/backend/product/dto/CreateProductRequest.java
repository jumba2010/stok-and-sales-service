package provenda.pos.backend.product.dto;

import lombok.Data;
import provenda.pos.backend.product.entity.ProductEntity;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class CreateProductRequest {

    @NotNull
    private String name;

    @NotNull
    private String code;

    @NotNull
    private String description;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long unitId;

    @Min(0)
    private int alertQuantity;

    @Min(0)
    private int initialStock;

    @Min(1)
    private double purchasePrice;

    @Min(1)
    private double salePrice;

    @NotNull
    private Long supplierId;

    public static ProductEntity getProductInstance(CreateProductRequest createProductRequest) {
        return ProductEntity.builder()
                .name(createProductRequest.getName())
                .code(createProductRequest.getCode())
                .description(createProductRequest.getDescription())
                .categoryId(createProductRequest.getCategoryId())
                .unitId(createProductRequest.getUnitId())
                .alertQuantity(createProductRequest.getAlertQuantity())
                .availableQuantity(createProductRequest.getInitialStock())
                .purchasePrice(BigDecimal.valueOf(createProductRequest.getPurchasePrice()))
                .salePrice(BigDecimal.valueOf(createProductRequest.getSalePrice()))
                .currentPrice(BigDecimal.valueOf(createProductRequest.getSalePrice()))
                .supplierId(createProductRequest.getSupplierId())
                .canBeSold(true)
                .build();
    }
}
