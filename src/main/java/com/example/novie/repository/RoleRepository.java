package com.example.novie.repository;

import com.example.novie.model.Role;
import com.example.novie.model.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role,Long> {
    boolean existsByName(RoleName name);
    Optional<Role> findByName(RoleName name);
}
