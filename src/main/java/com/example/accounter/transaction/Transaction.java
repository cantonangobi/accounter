package com.example.accounter.transaction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @SequenceGenerator(
        name = "transaction_sequence",
        sequenceName = "transaction_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "transaction_sequence"
    )
    private Long transactionId;
    private Long userId;
    private Long accountId;
    private String accountName;
    private String type;
    private Double amount;
    private Double balance;

    // public Transaction(String type, Double amount, Double balance){
    //     this.type = type;
    //     this.amount = amount;
    //     this.balance = balance;
    // }
    
    public Transaction(Long userId, Long accountId, String accountName, String type, Double amount, Double balance){
        this.userId = userId;
        this.accountId = accountId;
        this.accountName = accountName;                
        this.type = type;
        this.amount = amount;
        this.balance = balance;
    }
}
