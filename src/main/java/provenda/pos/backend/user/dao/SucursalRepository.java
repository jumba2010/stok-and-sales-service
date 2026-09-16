package provenda.pos.backend.user.dao;

import org.springframework.stereotype.Repository;
import provenda.pos.backend.generic.dao.AbstractBaseRepository;
import provenda.pos.backend.user.entity.SucursalEntity;

@Repository
public interface SucursalRepository extends AbstractBaseRepository<SucursalEntity, Long> {
}
