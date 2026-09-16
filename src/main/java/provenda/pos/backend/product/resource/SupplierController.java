package provenda.pos.backend.product.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import provenda.pos.backend.exceptions.BusinessException;
import provenda.pos.backend.product.dto.CreateSupplierRequest;
import provenda.pos.backend.product.entity.SupplierEntity;
import provenda.pos.backend.product.service.SupplierService;
import provenda.pos.backend.security.UserContext;

import javax.validation.Valid;

@RestController
@RequestMapping("suppliers")
public class SupplierController {

    private final SupplierService supplierService;
    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @PostMapping
    public ResponseEntity<Void> createSupplier(@RequestBody @Valid CreateSupplierRequest createSupplierRequest)
        throws BusinessException{

        var supplier = supplierService.createSupplier(
                UserContext.getDefaultContext(),
                CreateSupplierRequest.getSupplierInstance(createSupplierRequest)
        );
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{supplierId}")
    public ResponseEntity<Void> updateSupplier(@PathVariable Long supplierId, @RequestBody @Valid CreateSupplierRequest createSupplierRequest)
        throws BusinessException{

        SupplierEntity updatedSupplier = CreateSupplierRequest.getSupplierInstance(createSupplierRequest);
        supplierService.updateSupplier(UserContext.getDefaultContext(), supplierId, updatedSupplier);
        return ResponseEntity.ok().build();
    }




}
