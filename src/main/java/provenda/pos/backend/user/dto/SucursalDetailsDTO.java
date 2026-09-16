package provenda.pos.backend.user.dto;

import lombok.Builder;
import lombok.Data;
import provenda.pos.backend.product.dto.ProductAdjustmentDTO;
import provenda.pos.backend.stock.dto.InventoryDetailsDTO;
import provenda.pos.backend.stock.entity.InventoryAdjustmentEntity;
import provenda.pos.backend.stock.entity.InventoryEntity;
import provenda.pos.backend.user.entity.SucursalEntity;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
public class SucursalDetailsDTO {
    private String code;
    private String name;
    private String nuit;
    private String address;
    private String contact;

    public static SucursalDetailsDTO getSucursalDetails(SucursalEntity sucursalEntity) {
        return SucursalDetailsDTO.builder()
                .code(sucursalEntity.getCode())
                .name(sucursalEntity.getName())
                .nuit(sucursalEntity.getTaxId())
                .address(sucursalEntity.getAddress())
                .contact(sucursalEntity.getContact())
                .build();
    }
}

