package provenda.pos.backend.generic.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import provenda.pos.backend.generic.entity.LifeCyCleState;
import provenda.pos.backend.generic.entity.LifeCycleEntity;

/**
 * @author Judiao Mbaua
 *
 */
public interface AbstractQueryService<T extends LifeCycleEntity<ID>, ID extends Serializable> {

Optional<T> findById(ID id);

List<T> findAll();

List<T> findByActiveAndState(LifeCyCleState lifeCyCleState);


}
