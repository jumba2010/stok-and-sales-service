package provenda.pos.backend.product.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import provenda.pos.backend.product.entity.ProductEntity;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductResponseDTO {

    private String name;
    private String code;
    private CreateCategoryRequest category;
    private double purchasePrice;
    private double salePrice;
    private int initialStock;
    private int alertQuantity;
    private String unit;
    private String supplierId;
    private String description;

    @PostConstruct
    public static List<ProductResponseDTO> getProducts(List<ProductEntity> products) {
        return products.stream().map(product ->
                ProductResponseDTO.builder()
                        .name(product.getName())
                        .code(product.getCode())
                        .category(product.getCategory() != null ? CreateCategoryRequest.fromEntity(product.getCategory()) : null)
                        .purchasePrice(builder().purchasePrice)
                        .salePrice(builder().salePrice)
                        .initialStock(product.getAvailableQuantity())
                        .alertQuantity(product.getAlertQuantity())
                        .unit(String.valueOf(product.getUnitId()))
                        .description(product.getDescription())

                        .build()
                    ).collect(Collectors.toList());
    }

    public static ProductResponseDTO fromEntity(ProductEntity productEntity) {
        return ProductResponseDTO.builder()
                .name(productEntity.getName())
                .code(productEntity.getCode())
                .category(productEntity.getCategory() != null
                        ? CreateCategoryRequest.fromEntity(productEntity.getCategory())
                        : null)
                .purchasePrice(productEntity.getPurchasePrice() != null
                        ? productEntity.getPurchasePrice().doubleValue()
                        : 0.0)
                .salePrice(productEntity.getSalePrice() != null
                        ? productEntity.getSalePrice().doubleValue()
                        : 0.0)
                .initialStock(productEntity.getAvailableQuantity())
                .alertQuantity(productEntity.getAlertQuantity())
                .unit(productEntity.getUnitId() != null
                        ? String.valueOf(productEntity.getUnitId())
                        : null)
                .supplierId(productEntity.getSupplierId() != null
                        ? String.valueOf(productEntity.getSupplierId())
                        : null)
                .description(productEntity.getDescription())
                .build();
    }


}
