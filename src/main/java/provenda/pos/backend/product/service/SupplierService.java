package provenda.pos.backend.product.service;

import provenda.pos.backend.exceptions.BusinessException;
import provenda.pos.backend.product.entity.SupplierEntity;
import provenda.pos.backend.security.UserContext;

public interface SupplierService {
    SupplierEntity createSupplier(UserContext userContext, SupplierEntity supplierEntity) throws BusinessException;

    void updateSupplier(UserContext userContext, Long SupplierId, SupplierEntity supplierEntity) throws BusinessException;


}
