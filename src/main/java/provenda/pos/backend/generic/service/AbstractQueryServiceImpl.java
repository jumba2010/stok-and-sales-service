package provenda.pos.backend.generic.service;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import provenda.pos.backend.generic.dao.AbstractBaseRepository;
import provenda.pos.backend.generic.entity.LifeCyCleState;
import provenda.pos.backend.generic.entity.LifeCycleEntity;


@Transactional
public class AbstractQueryServiceImpl<T extends LifeCycleEntity<ID>, ID extends Serializable>
		implements AbstractQueryService<T, ID> {

	private final AbstractBaseRepository<T, ID> repository;

    public AbstractQueryServiceImpl(AbstractBaseRepository<T, ID> repository) {
        this.repository = repository;
    }

    @Override
	public Optional<T> findById(ID id) {
		
		return repository.findById(id);
	}

	@Override
	public List<T> findAll() {
		return (List<T>) repository.findAll();
	}

	@Override
	public List<T> findByActiveAndState(LifeCyCleState lifeCyCleState) {
	
		return repository.findByActiveAndState(lifeCyCleState.isActive(),lifeCyCleState.getState());
	}

}
