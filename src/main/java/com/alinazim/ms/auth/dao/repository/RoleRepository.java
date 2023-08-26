package com.alinazim.ms.auth.dao.repository;

import com.alinazim.ms.auth.dao.entity.RoleEntity;
import com.alinazim.ms.auth.model.enums.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<RoleEntity, Long> {
    Optional<RoleEntity> findByName(UserRole name);

}
