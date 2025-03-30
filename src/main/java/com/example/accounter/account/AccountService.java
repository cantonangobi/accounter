package com.example.accounter.account;

import java.util.List;

import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;

    public List<Account> getAccounts(){
        return accountRepository.findAll();
    }

    // public Account geAccount(String name){
    //     return accountRepository.findAccountByName(name)
    // }
    public String addNewAccount(Account account){
        //todo: implement add account functionality

        return "Account added";
    }
}
