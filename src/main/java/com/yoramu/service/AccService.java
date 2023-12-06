package com.yoramu.service;

import com.yoramu.model.Account;
import com.yoramu.repo.AccountRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AccService implements UserDetailsService {
    private final AccountRepo accountRepo;

    @Autowired
    public AccService(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return accountRepo.findAccountByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    public Account createAccount(Account account) {
        return accountRepo.save(account);
    }

    static Account getLoggedInUser() {
        return (Account) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
