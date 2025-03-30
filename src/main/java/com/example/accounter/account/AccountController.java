package com.example.accounter.account;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accounter.user.AppUser;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
// @RequestMapping(path = "/api/v1/account")
public class AccountController {
    private AccountService accountService;

    @PostMapping(path = "/api/v1/account", consumes = "application/json")
    public String addNewAccount(@RequestBody AccountRequest request){

        Account newAccount = new Account(request.getName(), request.getBalance());
        return accountService.addNewAccount(newAccount);
    }

    @RequestMapping("/accounts")
    public String accounts(){
        
        List<Account> accountList = accountService.getAccounts();
        String result = "";

        for (Account acc : accountList){
            result += acc.getName() + " ";
        }
        return result;

    }

}
