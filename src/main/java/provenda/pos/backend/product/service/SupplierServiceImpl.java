package provenda.pos.backend.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import provenda.pos.backend.exceptions.BusinessException;
import provenda.pos.backend.generic.service.AbstractServiceImpl;
import provenda.pos.backend.product.dao.SupplierRepository;
import provenda.pos.backend.product.entity.SupplierEntity;
import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.utils.BusinessConstants;
import provenda.pos.backend.utils.ValidationUtils;

import javax.validation.ValidationException;

@Service
public class SupplierServiceImpl extends AbstractServiceImpl<SupplierEntity, Long> implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierServiceImpl(SupplierRepository supplierRepository) {
        super(supplierRepository);
        this.supplierRepository = supplierRepository;

    }

    @Override
    public SupplierEntity createSupplier(UserContext userContext, SupplierEntity supplier) throws BusinessException{
        ValidationUtils.validateSupplierCreation(supplierRepository, supplier);
        this.create(userContext, supplier);
        return supplier;
    }

    public void updateSupplier(UserContext userContext, Long supplierId, SupplierEntity updatedSupplier) throws BusinessException{
        ValidationUtils.validateSupplierUpdate(supplierRepository, supplierId, updatedSupplier);

        SupplierEntity existingSupplier = supplierRepository.findById(supplierId).orElseThrow(() -> new ValidationException(BusinessConstants.SUPPLIER_NOT_FOUND));

        existingSupplier.setName(updatedSupplier.getName());
        existingSupplier.setContact(updatedSupplier.getContact());
        existingSupplier.setEmail(updatedSupplier.getEmail());
    }


}
