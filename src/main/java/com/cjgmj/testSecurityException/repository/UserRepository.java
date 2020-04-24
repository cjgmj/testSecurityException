package com.cjgmj.testSecurityException.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cjgmj.testSecurityException.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Long> {

	@Query("SELECT u FROM User u JOIN FETCH u.roles WHERE u.username = :username")
	public Optional<UserEntity> findByUsername(String username);
}
