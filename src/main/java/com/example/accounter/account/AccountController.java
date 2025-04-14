package com.example.accounter.account;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    
    @RequestMapping("/getaccounts")
    public List<Account> getAccounts(){   
        return accountService.getAccounts();
    }
    @RequestMapping("/getaccount/{id}")
    public Account geAccount(@PathVariable("id") Long accountId){
        return accountService.getAccount(accountId);
    }
    
    @PostMapping(path = "/create", consumes = "application/json")
    public String createAccount(@RequestBody AccountRequest request){
        Account newAccount = new Account(request.getName(), request.getBalance());
        return accountService.createAccount(newAccount);
    }

    @PostMapping(path = "/update/{id}", consumes = "application/json")
    public String updateAccount(@RequestBody AccountRequest request){
        Account newAccount = new Account(request.getName(), request.getBalance());
        return accountService.updateAccount(request.getAccountId(), newAccount);
    }

    @DeleteMapping(path = "/delete/{id}")
    public String deleteAccount(@PathVariable("id") Long accountId){
        String response = "";
        String transactionDeleted = transactionService.deleteAccountTransactions(accountId);
        String accountDeleted = accountService.deleteAccount(accountId);

        if (transactionDeleted.equals("Success") && accountDeleted.equals("Success")) {
            response = "Success";
        }
        else{
            response =  transactionDeleted + "\n" + accountDeleted;
        }

        return response;
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
