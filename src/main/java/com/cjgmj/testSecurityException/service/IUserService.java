package com.cjgmj.testSecurityException.service;

import java.util.List;

import com.cjgmj.testSecurityException.entity.UserEntity;

public interface IUserService {

	public List<UserEntity> findAll();

	public UserEntity findByUsername(String username);

	public UserEntity registerUser(UserEntity user);

}
