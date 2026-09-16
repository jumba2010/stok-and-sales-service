package provenda.pos.backend.product.dao;

import provenda.pos.backend.generic.dao.AbstractBaseRepository;
import provenda.pos.backend.product.entity.SupplierEntity;

import java.util.Optional;

public interface SupplierRepository extends AbstractBaseRepository<SupplierEntity, Long> {
    Optional<SupplierEntity> findByName(final String name);
}
