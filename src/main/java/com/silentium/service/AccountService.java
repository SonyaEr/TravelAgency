package com.silentium.service;


import com.silentium.model.Account;
import com.silentium.model.Person;
import com.silentium.model.Role;
import com.silentium.repository.AccountRepository;
import com.silentium.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.Collection;
import java.util.Date;


@Service("accountService")
public class AccountService {

    private AccountRepository accountRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;


    @Autowired
    public AccountService(AccountRepository accountRepository,
                          RoleRepository roleRepository,
                          BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.accountRepository = accountRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public Account findUserByLogin(String login) {
        return accountRepository.findByLogin(login);
    }

    public Person findPersonByLogin(String login) {
        return accountRepository.findPersonByLogin(login);
    }

    public Account saveAccount(Account account) {
        account.setLogin(account.getLogin());
        account.setPassword(bCryptPasswordEncoder.encode(account.getPassword()));
        account.setActive(1);
        account.setDateJoined(new Date());
        Role userRole = roleRepository.findByRole("User");
        account.setRole(userRole);
        return accountRepository.save(account);
    }


}
