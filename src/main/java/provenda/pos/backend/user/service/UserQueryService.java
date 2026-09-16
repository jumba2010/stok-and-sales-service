package provenda.pos.backend.user.service;

import java.util.List;
import java.util.Set;

import provenda.pos.backend.user.entity.UserEntity;

public interface UserQueryService {
	
	List<UserEntity> findUserBySucursalIds(Set<Long> sucursalIds );
}
