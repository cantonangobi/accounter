package com.example.accounter.account;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
@ToString
public class AccountRequest {
    private final Long accountId;
    private final String name;
    private final Double balance;
}
