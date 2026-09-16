package provenda.pos.backend.generic.service;

import java.io.Serializable;

import provenda.pos.backend.security.UserContext;


/**
 * @author Judiao Mbaua
 *
 */
public interface AbstractService<T, ID extends Serializable> {

T create(UserContext userContext,T entity);

T update(UserContext userContext,T entity);

void inativate(UserContext userContext,T entity);

void activate(UserContext userContext,T entity);

void delete(UserContext userContext,T entity);

void banish(UserContext userContext,T entity);

}
