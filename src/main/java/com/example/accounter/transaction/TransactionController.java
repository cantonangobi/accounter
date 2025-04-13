package com.example.accounter.transaction;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.accounter.account.Account;
import com.example.accounter.account.AccountService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/transaction")
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountService accountService;

    @PostMapping("/create")
    public String createTransaction(@RequestBody TransactionRequest request){

        Account account = accountService.getAccount(request.getAccountName());
        Transaction transaction = new Transaction(account.getUserId(), 
                                                    account.getAccountId(), 
                                                    account.getName(),
                                                    request.getCategory(), 
                                                    request.getType(), 
                                                    request.getAmount());

        return transactionService.createTransaction(transaction, account);
    }

    @PostMapping(path = "/changebalance", consumes = "application/json")
    public String changeBalance(@RequestBody TransactionRequest request){
        return transactionService.changeBalance(request.getAccountName(), request.getAmount());
    }

    @RequestMapping(path = "/getusertransactions")
    public List<Transaction> getUserTransactions(){
        return transactionService.getUserTransactions();
    }

    @RequestMapping(path = "/getaccounttransactions")
    public List<Transaction> getAccountTransactions(@RequestBody TransactionRequest request){
        return transactionService.getAccountTransactions(request.getAccountName());
    }

    @DeleteMapping(path = "/delete", consumes = "application/json")
    public String deleteTransaction(@RequestBody TransactionRequest request){
        return transactionService.deleteById(request.getTransactionId());
    }

    @DeleteMapping(path = "/deleteaccounttransactions", consumes = "application/json")
    public String deleteAccountTransactions(@RequestBody TransactionRequest request){
        return transactionService.deleteAccountTransactions(request.getAccountName());
    }

    @PostMapping(path = "/update", consumes = "application/json")
    public String updateTransaction(@RequestBody TransactionRequest request){
        Account account = accountService.getAccount(request.getAccountName());
        // Transaction transaction = transactionService.getById(request.getTransactionId());
        Transaction transaction = new Transaction(account.getUserId(), 
                                                    account.getAccountId(), 
                                                    account.getName(),
                                                    request.getCategory(), 
                                                    request.getType(), 
                                                    request.getAmount());

        return transactionService.updateTransaction(request.getTransactionId(), transaction);
    }   

}
