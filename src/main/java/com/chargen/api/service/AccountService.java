package com.chargen.api.service;

import com.chargen.api.entity.Account;
import com.chargen.api.entity.Role;
import com.chargen.api.repository.AccountRepository;
import com.chargen.api.repository.RoleRepository;
import com.chargen.api.service.dto.AccountDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepository roleRepository;

    public AccountService(AccountRepository accountRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    public Account createNewAccount(AccountDto accountDto) {
        Account account = new Account();
        account.setUsername(accountDto.getUsername());
        account.setPassword(passwordEncoder.encode(accountDto.getPassword()));
        System.out.println("pass: " + account.getPassword());
        Role userRole = roleRepository.findByName("ROLE_USER");
        userRole.assignToAccount(account);
        return accountRepository.save(account);
    }

    public List<Account> getAllAccounts() {
        return accountRepository.findAll();
    }
}
