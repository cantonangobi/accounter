package com.example.accounter.transaction;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.accounter.account.Account;
import com.example.accounter.account.AccountService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;
    private final AccountService accountService;

    @PostMapping("/api/v1/transaction/add")
    public String addTransaction(@RequestBody TransactionRequest request){

        Account account = accountService.getAccount(request.getAccountName());
        Transaction transaction = new Transaction(account.getAccountId(), account.getUserId(), request.getType(), request.getAmount(), request.getBalance());

        return transactionService.addTransaction(transaction, account);
    }

    @PostMapping(path = "/api/v1/transaction/changebalance", consumes = "application/json")
    public String changeBalance(@RequestBody TransactionRequest request){
        return transactionService.changeBalance(request.getAccountName(), request.getAmount());
    }
}
