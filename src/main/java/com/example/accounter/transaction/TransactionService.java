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

    public String changeBalance(String name, Double amount){
        // this.user = this.userService.getSessionUser();
        Account account = accountService.getAccount(name);

        if (account == null){
            System.out.println("Account doesn't exist");
            return "Account doesn't exist";
        }

        Double transactionAmount = Math.abs(amount - account.getBalance());
        String transactionType;
    
        if (amount == account.getBalance()){
            return "No Change in Balance";
        }
        if (amount > account.getBalance()){
            transactionType = "Income";
        }
        else{
            transactionType = "Expense";
        }
        
        Transaction transaction = new Transaction(account.getUserId(), 
                                                    account.getAccountId(), 
                                                    account.getName(),
                                                    "Adjustment", 
                                                    transactionType, 
                                                    transactionAmount);
        
        return this.createTransaction(transaction, account);
        
        
    }

    public List<Transaction> getUserTransactions(){
        AppUser user = accountService.getUser();
        return transactionRepository.findAllByUserId(user.getUserId()).get();
    }

    public Transaction getTransactionById(Long transactionId){
        Long userId = accountService.getUser().getUserId();
        System.out.println("Test 1");
        Transaction transaction =  transactionRepository.getReferenceById(transactionId);
        if(transaction.getUserId() == userId){
            System.out.println("Transaction doesn't exist");
            return null;
        }

        return transaction;
    }

    public List<Transaction> getAccountTransactions(String accountName){
        Account account = accountService.getAccount(accountName);
        return transactionRepository.findAllByAccountId(account.getAccountId()).get();
    }

    public String deleteTransactionById(Long transactionId){
        Transaction transaction = getTransactionById(transactionId);
        if (transaction == null){
            System.out.println("Transaction does not exist");
            return "Transaction does not exist";
        }
        transactionRepository.delete(transaction);
        return "Transaction Deleted";
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

    public String updateTransaction(Long transactionId, Transaction newTransaction){
        boolean transactionExists = transactionRepository.existsById(transactionId);
        if (!transactionExists){
            System.out.println("Transaction does not exist");
            return "Transaction does not exist";
        }

        Transaction currentTransaction = transactionRepository.getReferenceById(transactionId);
        currentTransaction.setCategory(newTransaction.getCategory());
        currentTransaction.setAmount(newTransaction.getAmount());
        currentTransaction.setType(newTransaction.getType());

        transactionRepository.save(currentTransaction);
        return "Success";
    }

}
