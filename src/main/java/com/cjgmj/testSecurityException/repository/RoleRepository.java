package com.cjgmj.testSecurityException.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cjgmj.testSecurityException.entity.RoleEntity;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {

	public Optional<RoleEntity> findByAuthority(String authority);
}
