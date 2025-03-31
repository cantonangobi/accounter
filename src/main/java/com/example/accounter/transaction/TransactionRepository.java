package com.example.accounter.transaction;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long>{
    Optional<List<Transaction>> findAllByUserId(Long userId);
    Optional<List<Transaction>> findAllByAccountId(Long accountId);
    void deleteAllByAccountId(Long accountId);

}
