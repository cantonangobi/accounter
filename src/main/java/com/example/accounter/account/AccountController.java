package com.example.accounter.account;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/account")
public class AccountController {
    private AccountService accountService;

    @PostMapping(consumes = "application/json")
    public String addNewAccount(@RequestBody AccountRequest request){

        //todo: get the actual userId
        Long userId = (long) 1;
        
        return accountService.addNewAccount(new Account(userId, request.getName(), request.getBalance()));
    }

}
