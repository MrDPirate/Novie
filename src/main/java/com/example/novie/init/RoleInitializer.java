package com.example.novie.init;

import com.example.novie.model.Role;
import com.example.novie.model.RoleName;
import com.example.novie.repository.RoleRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class RoleInitializer {

    private final RoleRepository roleRepository;



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
