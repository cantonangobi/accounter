package com.example.accounter.transaction;

import org.springframework.stereotype.Service;

import com.example.accounter.account.Account;
import com.example.accounter.account.AccountService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;


    public String addTransaction(Transaction transaction, Account account){
        // Account account = accountService.g
        Double balance = account.getBalance();
        if (transaction.getType().equals("Expense")){
            balance = balance - transaction.getAmount();        
        }
        else if (transaction.getType().equals( "Income")){
            balance = balance + transaction.getAmount();
        }

        account.setBalance(balance);
        accountService.editAccount(account);
        transaction.setBalance(balance);
        transactionRepository.save(transaction);

        return "Transaction recorded";
    }

    public String changeBalance(String name, Double balance){
        // this.user = this.userService.getSessionUser();
        Account account = accountService.getAccount(name);

        if (account == null){
            System.out.println("Account doesn't exist");
            return "Account doesn't exist";
        }

        Double transactionAmount = Math.abs(balance - account.getBalance());
        String transactionType;
    
        if (balance > account.getBalance()){
            transactionType = "Income";
        }
        else{
            transactionType = "Expense";
        }
        
        Transaction transaction = new Transaction(account.getAccountId(), account.getUserId(), transactionType, transactionAmount, balance);
        
        return this.addTransaction(transaction, account);
        
        
    }
}
