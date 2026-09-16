package provenda.pos.backend.user.dao;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import provenda.pos.backend.generic.dao.AbstractBaseRepository;
import provenda.pos.backend.user.entity.UserEntity;

@Repository
public interface UserRepository extends AbstractBaseRepository<UserEntity
, Long> {

	@Query("Select u from UserEntity u INNER JOIN FETCH u.profile INNER JOIN FETCH u.sucursal WHERE u.sucursalId IN :sucursalIds ")
    List<UserEntity> findUserBySucursalIds(@Param("sucursalIds") Set<Long> sucursalIds);
}
