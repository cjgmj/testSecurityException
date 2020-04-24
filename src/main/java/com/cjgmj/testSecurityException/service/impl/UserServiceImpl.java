package com.cjgmj.testSecurityException.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cjgmj.testSecurityException.entity.RoleEntity;
import com.cjgmj.testSecurityException.entity.UserEntity;
import com.cjgmj.testSecurityException.repository.RoleRepository;
import com.cjgmj.testSecurityException.repository.UserRepository;
import com.cjgmj.testSecurityException.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public List<UserEntity> findAll() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username).orElse(null);
	}

	@Override
	public UserEntity createUser(UserEntity user) {
		RoleEntity role = roleRepository.findByAuthority("ROLE_USER").orElse(null);

		if (role != null) {
			List<RoleEntity> roles = new ArrayList<RoleEntity>();

			roles.add(role);

			user.setRoles(roles);
		}

		return userRepository.save(user);
	}

}
