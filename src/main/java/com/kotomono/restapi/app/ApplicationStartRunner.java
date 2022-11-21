package com.kotomono.restapi.app;

import com.kotomono.restapi.model.Role;
import com.kotomono.restapi.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import static java.util.Arrays.asList;

@Component
@RequiredArgsConstructor
public class ApplicationStartRunner implements CommandLineRunner {
    private final RoleRepository roleRepository;

    @Override
    public void run(String... args) throws Exception {
        Role roleUser = new Role(1L, "USER", "ROLE_USER");
        Role roleAdmin = new Role(2L, "ADMIN", "ROLE_ADMIN");

//        roleRepository.saveAll(asList(roleUser, roleAdmin));
    }
}
