package com.example.accounter.account;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Account {
    @Id
    @SequenceGenerator(
        name = "account_sequence",
        sequenceName = "account_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "account_sequence"
    )
    private Long accountId;
    private Long userId;
    private String name;
    private Double balance;

    public Account(Long userId, String name, Double balance){
        this.userId = userId;
        this.name = name;
        this.balance = balance;
    }
}
