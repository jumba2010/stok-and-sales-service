package provenda.pos.backend.product.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import provenda.pos.backend.generic.dao.AbstractBaseRepository;
import provenda.pos.backend.product.entity.ProductEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends AbstractBaseRepository<ProductEntity
, Long> , JpaSpecificationExecutor<ProductEntity>  {

	@Query("Select p from ProductEntity p INNER JOIN FETCH p.unit INNER JOIN FETCH p.category WHERE p.sucursalId = :sucursalId AND p.active = :active and p.state =:state")
    List<ProductEntity> findProductBySucursalIdAndActiveAndState(@Param("sucursalId") Long sucursalId, @Param("active") boolean active, @Param("state") int state);

	Optional<ProductEntity> findByCode(final String code);


	@Query("SELECT p FROM ProductEntity p WHERE p.active = :active AND p.state = :state")
	Page<ProductEntity> findAllByActiveAndState(@Param("active") boolean active, @Param("state") int state, Pageable pageable);

	@Query("SELECT p FROM ProductEntity p WHERE p.supplierId = :supplierId AND p.active = :active AND p.state = :state")
	Page<ProductEntity> findBySupplierIdAndActiveAndState(@Param("supplierId") Long supplierId, @Param("active") boolean active, @Param("state") int state, Pageable pageable);
}
