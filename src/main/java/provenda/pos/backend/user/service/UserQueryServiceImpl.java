package provenda.pos.backend.user.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import provenda.pos.backend.user.dao.UserRepository;
import provenda.pos.backend.user.entity.UserEntity;

@Service
public class UserQueryServiceImpl implements UserQueryService {

	@Autowired
	private UserRepository userRepo;

	@Override
	public List<UserEntity> findUserBySucursalIds(Set<Long> sucursalIds) {
		return userRepo.findUserBySucursalIds(sucursalIds);
	}

}
