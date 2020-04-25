package com.cjgmj.testSecurityException.service;

import java.util.List;

import com.cjgmj.testSecurityException.entity.UserEntity;
import com.cjgmj.testSecurityException.exception.DefaultException;

public interface IUserService {

	public List<UserEntity> findAll();

	public UserEntity findByUsername(String username) throws DefaultException;

	public UserEntity registerUser(UserEntity user);

}
