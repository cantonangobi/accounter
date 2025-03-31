package com.example.accounter.account;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accounter.transaction.TransactionService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping(path = "/api/v1/account")
public class AccountController {
    private AccountService accountService;
    private TransactionService transactionService;

    @PostMapping(path = "/create", consumes = "application/json")
    public String createAccount(@RequestBody AccountRequest request){
        Account newAccount = new Account(request.getName(), request.getBalance());
        return accountService.createAccount(newAccount);
    }

    @PostMapping(path = "/update", consumes = "application/json")
    public String updateAccount(@RequestBody AccountRequest request){
        Account newAccount = new Account(request.getName(), request.getBalance());
        return accountService.updateAccount(newAccount);
    }

    @DeleteMapping(path = "/delete", consumes = "application/json")
    public String deleteAccount(@RequestBody AccountRequest request){
        String response = "";
        response += transactionService.deleteAccountRecords(request.getName()) + "\n";
        response += accountService.deleteAccount(request.getName());
        return response;
    }

    @RequestMapping("/getaccounts")
    public List<Account> getAccounts(){   
        return accountService.getAccounts();
    }

    @RequestMapping("/displayaccounts")
    public String accounts(){   
        List<Account> accountList = accountService.getAccounts();
        String result = "";
        for (Account acc : accountList){
            result += acc.toString() + "\n";
        }
        return result;
    }

}
