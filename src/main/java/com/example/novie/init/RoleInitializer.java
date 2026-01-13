package com.example.novie.init;

import com.example.novie.model.Role;
import com.example.novie.model.RoleName;
import com.example.novie.repository.RoleRepository;
import jakarta.annotation.PostConstruct;

public class RoleInitializer {

    private final RoleRepository roleRepository;


    public RoleInitializer(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @PostConstruct
    public void init() {


        if (!roleRepository.existsByName(RoleName.ROLE_USER)) {
            roleRepository.save(new Role(null, RoleName.ROLE_USER));
        }

        if (!roleRepository.existsByName(RoleName.ROLE_ADMIN)) {
            roleRepository.save(new Role(null, RoleName.ROLE_ADMIN));
        }
    }
}
