package com.example.accounter.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.accounter.account.Account;

@Repository
@Transactional
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    Optional<Account> findByTransactionId(Long transactionId);
    Optional<Account> findByTransactionIdAndUserId(Long transactionId, Long userId);
    Optional<List<Transaction>> findAllByUserId(Long userId);
    Optional<List<Transaction>> findAllByAccountId(Long accountId);
    void deleteAllByAccountId(Long accountId);

}
