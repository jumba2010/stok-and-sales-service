package provenda.pos.backend.product.dto;

import lombok.Data;
import provenda.pos.backend.product.entity.SupplierEntity;

@Data
public class CreateSupplierRequest {
    private String name;
    private String contact;
    private String email;

    public static SupplierEntity getSupplierInstance(CreateSupplierRequest createSupplierRequest) {
        return SupplierEntity.builder()
                .name(createSupplierRequest.getName())
                .contact(createSupplierRequest.getContact())
                .email(createSupplierRequest.getEmail())
                .build();
    }
}
