package com.kotomono.restapi.service.impl;

import com.kotomono.restapi.model.Account;
import com.kotomono.restapi.model.Role;
import com.kotomono.restapi.repository.AccountRepository;
import com.kotomono.restapi.repository.RoleRepository;
import com.kotomono.restapi.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class JpaAccountService implements AccountService {
    private final AccountRepository accountRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;

    @Override
    public Account createAccount(Account account) {
        account.setPassword(encoder.encode(account.getPassword()));

        Role roleUser = roleRepository.findByName("ROLE_USER");
//        Role roleAdmin = roleRepository.findByName("ROLE_ADMIN");
        Set<Role> roles = new HashSet<>();

        roles.add(roleUser);
        account.setRoles(roles);

        return accountRepository.save(account);
    }

    @Override
    public Account findByUsername(String username) {
        return accountRepository.findByUsername(username);
    }

    @Override
    public List<Account> getAccounts() {
        return accountRepository.findAll();
    }
}
