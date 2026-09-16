package provenda.pos.backend.stock.dto;

import lombok.Builder;
import lombok.Data;
import provenda.pos.backend.product.dto.ProductAdjustmentDTO;
import provenda.pos.backend.stock.entity.InventoryAdjustmentEntity;
import provenda.pos.backend.stock.entity.InventoryEntity;
import provenda.pos.backend.user.dto.SucursalDetailsDTO;
import provenda.pos.backend.user.entity.SucursalEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class InventoryDetailsDTO {
    private Long inventoryId;
    private String date;
    private Long warehouseId;
    private Long createdById;
    private List<ProductAdjustmentDTO> products;
    private SucursalDetailsDTO sucursal; // Single SucursalDetailsDTO for the inventory

    public static InventoryDetailsDTO toInventoryDetailsDTO(
            InventoryEntity inventoryEntity,
            List<InventoryAdjustmentEntity> adjustments) {

        SucursalEntity sucursalEntity = inventoryEntity.getSucursal();
        SucursalDetailsDTO sucursalDetails = null;

        if (sucursalEntity != null) {
            sucursalDetails = SucursalDetailsDTO.builder()
                    .code(sucursalEntity.getCode())
                    .name(sucursalEntity.getName())
                    .nuit(sucursalEntity.getTaxId())
                    .address(sucursalEntity.getAddress())
                    .contact(sucursalEntity.getContact())
                    .build();
        }

        return InventoryDetailsDTO.builder()
                .inventoryId(inventoryEntity.getId())
                .date(inventoryEntity.getCreatedAt().toString())
                .warehouseId(inventoryEntity.getWarehouseId())
                .createdById(inventoryEntity.getCreatedBy())
                .sucursal(sucursalDetails)
                .products(adjustments.stream()
                        .map(ProductAdjustmentDTO::toProductAdjustmentDTO)
                        .collect(Collectors.toList()))
                .build();
    }

}
