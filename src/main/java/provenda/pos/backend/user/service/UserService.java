package provenda.pos.backend.user.service;

import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.user.entity.UserEntity;

public interface UserService {
	
	UserEntity createUser(UserContext userContext, UserEntity user);
	
}
