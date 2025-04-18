package com.example.accounter.transaction;

import java.time.LocalDate;

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
    private String category;
    private String type;
    private Double amount;
    private LocalDate date;

    // public Transaction(String type, Double amount, Double balance){
    //     this.type = type;
    //     this.amount = amount;
    //     this.balance = balance;
    // }
    
    public Transaction(Long userId, Long accountId, String accountName, String category, String type, Double amount, LocalDate date){
        this.userId = userId;
        this.accountId = accountId;
        this.accountName = accountName;    
        this.category = category;            
        this.type = type;
        this.amount = amount;
        this.date = date;
    }
    public Transaction(Long userId, Long accountId, String accountName, String category, String type, Double amount){
        this.userId = userId;
        this.accountId = accountId;
        this.accountName = accountName;    
        this.category = category;            
        this.type = type;
        this.amount = amount;
        this.date = LocalDate.now();
    }

    @Override
    public String toString() {
        String result = "AccountName: " + accountName + ", Category: " + category + ", Type: " + type + ", Amount: " + amount;
        return result;
    }
}
