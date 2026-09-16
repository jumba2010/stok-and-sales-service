package provenda.pos.backend.generic.service;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.transaction.Transactional;

import provenda.pos.backend.generic.dao.AbstractBaseRepository;
import provenda.pos.backend.generic.entity.LifeCyCleState;
import provenda.pos.backend.generic.entity.LifeCycleEntity;
import provenda.pos.backend.security.UserContext;

/**
 * @author Judiao Mbaua
 *
 *         <p>
 *         Implementation of the generic repository
 *         </p>
 */
@Transactional
public abstract class AbstractServiceImpl<T extends LifeCycleEntity<ID>, ID extends Serializable>
		implements AbstractService<T, ID> {

	private final AbstractBaseRepository<T, ID> repository;

	protected AbstractServiceImpl(AbstractBaseRepository<T, ID> repository) {
		this.repository = repository; // Injected by the subclass
	}

	@Override
	public T create(UserContext userContext, T entity) {
		prepareEntityForCreate(userContext, entity);
		return repository.save(entity);
	}

	@Override
	public T update(UserContext userContext, T entity) {
		prepareEntityForUpdate(userContext, entity);
		return repository.save(entity);
	}

	@Override
	public void inativate(UserContext userContext, T entity) {
		prepareEntityForLifecycleChange(userContext, entity, LifeCyCleState.INACTIVE);
		repository.save(entity);
	}

	@Override
	public void activate(UserContext userContext, T entity) {
		prepareEntityForLifecycleChange(userContext, entity, LifeCyCleState.ACTIVE);
		repository.save(entity);
	}

	@Override
	public void delete(UserContext userContext, T entity) {
		prepareEntityForLifecycleChange(userContext, entity, LifeCyCleState.DELETED);
		repository.save(entity);
	}

	@Override
	public void banish(UserContext userContext, T entity) {
		prepareEntityForLifecycleChange(userContext, entity, LifeCyCleState.BANNED);
		repository.save(entity);
	}

	// Common method for setting create-specific attributes
	public void prepareEntityForCreate(UserContext userContext, T entity) {
		entity.setCreatedBy(userContext.getId());
		entity.setActivatedBy(userContext.getId());
		entity.setActive(LifeCyCleState.ACTIVE.isActive());
		entity.setState(LifeCyCleState.ACTIVE.getState());
		entity.setSucursalId(userContext.getSucursalId());
	}

	// Common method for setting update-specific attributes
	protected void prepareEntityForUpdate(UserContext userContext, T entity) {
		entity.setUpdatedBy(userContext.getId());
		entity.setUpdatedAt(LocalDateTime.now());
	}

	// Common method for lifecycle state changes (activate, inactivate, delete, etc.)
	protected void prepareEntityForLifecycleChange(UserContext userContext, T entity, LifeCyCleState state) {
		entity.setActivatedBy(userContext.getId());
		entity.setActive(state.isActive());
		entity.setState(state.getState());
	}
}

