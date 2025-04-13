package com.example.accounter.transaction;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class TransactionRequest {
    private Long transactionId;
    private String accountName;
    private String category;
    private String type;
    private Double amount;
}
