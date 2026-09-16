package provenda.pos.backend.user.service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import provenda.pos.backend.generic.service.AbstractServiceImpl;

import provenda.pos.backend.security.UserContext;
import provenda.pos.backend.user.dao.UserRepository;
import provenda.pos.backend.user.entity.UserEntity;

@Service
public class UserServiceImpl  extends AbstractServiceImpl<UserEntity, Long> implements UserService {

	@Autowired
	public UserServiceImpl(UserRepository userRepository) {
		super(userRepository);
	}
	@Override
	public UserEntity createUser(UserContext userContext,UserEntity user) {
			super.create(userContext, user);
	return user;
	
	}
}
