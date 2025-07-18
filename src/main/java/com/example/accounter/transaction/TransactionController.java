package com.example.accounter.transaction;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
                                                    request.getAmount(),
                                                    request.getDate()
                                                    );

        return transactionService.createTransaction(transaction, account);
    }

    @RequestMapping(path = "/getusertransactions")
    public List<Transaction> getUserTransactions(){
        return transactionService.getUserTransactions();
    }

    @RequestMapping(path = "/getaccounttransactions")
    public List<Transaction> getAccountTransactions(@RequestBody TransactionRequest request){
        return transactionService.getAccountTransactions(request.getAccountName());
    }

    @RequestMapping(path = "/get/{id}")
    public Transaction getTransaction(@PathVariable("id") Long transactionId){
        return transactionService.getTransactionById(transactionId);

    }    

    
    @PostMapping(path = "/update/{id}", consumes = "application/json")
    public String updateTransaction(@PathVariable("id") long transactionId, @RequestBody TransactionRequest request){
        Account account = accountService.getAccount(request.getAccountName());
        Transaction transaction = new Transaction(account.getUserId(), 
                                                    account.getAccountId(), 
                                                    account.getName(),
                                                    request.getCategory(), 
                                                    request.getType(), 
                                                    request.getAmount(),
                                                    request.getDate()
                                                    );

        return transactionService.updateTransaction(transactionId, transaction);
    }   

    @DeleteMapping(path = "/delete/{id}")
    public String deleteTransaction(@PathVariable("id") Long transactionId){
        return transactionService.deleteTransactionById(transactionId);
    }

    @DeleteMapping(path = "/deleteaccounttransactions", consumes = "application/json")
    public String deleteAccountTransactions(@RequestBody TransactionRequest request){
        return transactionService.deleteAccountTransactions(request.getAccountName());
    }


}
