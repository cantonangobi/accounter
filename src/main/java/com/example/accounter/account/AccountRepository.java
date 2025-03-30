package com.example.accounter.account;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional(readOnly = true)
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByAccountId(Long accountId);
    Optional<List<Account>> findAllByUserId(Long userId);
    Optional<Account> findByName(String name);
    Optional<Account> findByUserIdAndName(Long userId, String name);
}

