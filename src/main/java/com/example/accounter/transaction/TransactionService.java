package com.example.accounter.transaction;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.accounter.account.Account;
import com.example.accounter.account.AccountService;
import com.example.accounter.user.AppUser;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final AccountService accountService;


    public String createTransaction(Transaction transaction, Account account){
        // Account account = accountService.g
        if (account == null){
            return "Account doesn't exist";
        }
        Double balance = account.getBalance();
        Double amount = transaction.getAmount();
        if (transaction.getType().equals("Expense")){
            amount = 0 - amount;
            transaction.setAmount(amount);
            // balance = balance - transaction.getAmount();        
        }
        // else if (transaction.getType().equals( "Income")){
        //     balance = balance + transaction.getAmount();
        // }
        balance = balance + amount;
        account.setBalance(balance);
        accountService.updateAccount(account.getAccountId(), account);
        // transaction.setBalance(balance);
        transactionRepository.save(transaction);

        return "Success";
    }

    public List<Transaction> getUserTransactions(){
        AppUser user = accountService.getUser();
        return transactionRepository.findAllByUserId(user.getUserId()).get();
    }

    public Transaction getTransactionById(Long transactionId){
        Long userId = accountService.getUser().getUserId();
        System.out.println("Test 1");
        Transaction transaction =  transactionRepository.getReferenceById(transactionId);
        if(!transaction.getUserId().equals(userId)){
            System.out.println("User id: " + userId);
            System.out.println("Tx User id: " + transaction.getUserId());
            System.out.println("tx_uid == uid " + (transaction.getUserId().equals(userId)));
            System.out.println("tx_uid + uid " + (transaction.getUserId() + userId));
            System.out.println("Transaction doesn't exist");
            return null;
        }

        return transaction;
    }

    // public List<LocalDate> getTransactionDates(Long accountId){
    //     Account account = accountService.getAccount(accountId);
    //     if(account == null){
    //         System.out.println();
    //     }
    // }

    public List<Transaction> getAccountTransactions(String accountName){
        Account account = accountService.getAccount(accountName);
        return transactionRepository.findAllByAccountId(account.getAccountId()).get();
    }

    
    public List<Transaction> getAccountTransactions(Long accountId){
        return transactionRepository.findAllByAccountId(accountId).get();
    }

    
    public String updateTransaction(Long transactionId, Transaction newTransaction){
        boolean transactionExists = transactionRepository.existsById(transactionId);
        if (!transactionExists){
            System.out.println("Transaction does not exist");
            return "Transaction does not exist";
        }

        newTransaction.setTransactionId(transactionId);

        Double amount = Math.abs(newTransaction.getAmount());
        if (newTransaction.getType() == "Expense") {
            amount = 0 - amount;
        }

        newTransaction.setAmount(amount);

        transactionRepository.save(newTransaction);
        return "Success";
    }

    public String deleteTransactionById(Long transactionId){
        Transaction transaction = getTransactionById(transactionId);
        if (transaction == null){
            System.out.println("Transaction does not exist");
            return "Transaction does not exist";
        }
        
        Account account = accountService.getAccount(transaction.getAccountId());
        Double newBalance = account.getBalance() - transaction.getAmount();
        account.setBalance(newBalance);
        
        accountService.updateAccount(account.getAccountId(), account);
        transactionRepository.delete(transaction);
        
        return "Success";
    }


    public String deleteAccountTransactions(Long accountId){
        // Account account = accountService.getAccount(accountName);
        // boolean accountExists = accountR
        // if (account == null){
        //     System.out.println("Account doesn't exist");
        //     return "Account doesn't exist";
        // }
        // System.out.println(account.toString());

        Account account = accountService.getAccount(accountId);
        if(account == null){
            System.out.println("Account doesn't exist");
            return "Account doesn't exist";
        }
        System.out.println(account);
        transactionRepository.deleteAllByAccountId(accountId);

        return "Success";
    }


    public String deleteAccountTransactions(String accountName){
        Account account = accountService.getAccount(accountName);
        if (account == null){
            System.out.println("Account doesn't exist");
            return "Account doesn't exist";
        }
        System.out.println(account.toString());
        transactionRepository.deleteAllByAccountId(account.getAccountId());

        return "Success";
    }

}
