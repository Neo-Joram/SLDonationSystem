package com.yoramu.repo;

import com.yoramu.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface AccountRepo extends JpaRepository<Account, UUID> {
    Optional<Account> findAccountByEmail(String email);
}
