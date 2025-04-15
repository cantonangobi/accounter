package com.example.accounter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.accounter.account.Account;
import com.example.accounter.account.AccountService;
import com.example.accounter.transaction.Transaction;
import com.example.accounter.transaction.TransactionService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AppController {
    private AccountService accountService;
    private TransactionService transactionService;

    @RequestMapping("/")
    public String index(Model model){
        // String userName = userService.getSessionUser().getUsername();
        // model.addAttribute("userName", userName);        
        List<Account> accounts = accountService.getAccounts();
        model.addAttribute("accounts", accounts);
        
        return "index";
    }

    @RequestMapping("/signup")
    public String signUp(){
        return "signup";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

//Account Section    
    @RequestMapping("/account-list")
    public String getAccountList(Model model){
        List<Account> accounts = accountService.getAccounts();
        model.addAttribute("accounts", accounts);        
        return "account-list";
    }

    @RequestMapping("/account-details/{id}")
    public String getAccountDetails(@PathVariable("id") Long accountId, Model model){
        Account account = accountService.getAccount(accountId);
        if (account == null) {
            return "resource-not-found";
        }
        model.addAttribute("account", account);

        List<Transaction> transactions = transactionService.getAccountTransactions(accountId);
        model.addAttribute("transactions", transactions);
        return "account-details";
    }

    @RequestMapping("/account-create")
    public String createAccount(){
        return "account-create";
    }

    @RequestMapping("/account-update/{id}")
    public String updateAccount(@PathVariable("id") Long accountId, Model model){
        Account account = accountService.getAccount(accountId);
        if (account == null) {
            return "resource-not-found";
        }
        model.addAttribute("account", account);
        return "account-update";
    }

    @RequestMapping("/deleteaccount")
    public String deleteAccount(Model model){
        List<Account> accounts = accountService.getAccounts();
        model.addAttribute("accounts", accounts);
        return "deleteaccount";
    }

    @RequestMapping("/account-delete-confirmation")
    public String accountDeleteConfirmation(){
        return "account-delete-confirmation";
    }

//Transactions section
    @RequestMapping("/transaction-create")
    public String createTransaction(Model model){
        List<Account> accounts = accountService.getAccounts();
        model.addAttribute("accounts", accounts);
        LocalDate today = LocalDate.now();
        model.addAttribute("today", today);
        return "transaction-create";
    }

    @RequestMapping("/transaction-list")
    public String getTransactionList(Model model){
        List<Account> accounts = accountService.getAccounts();
        model.addAttribute("accounts", accounts);

        List<Transaction> transactions = transactionService.getUserTransactions();
        model.addAttribute("transactions", transactions);

        List<LocalDate> dates = new ArrayList<>();
        for (Transaction transaction : transactions){
            if (!dates.contains(transaction.getDate())){
                dates.add(transaction.getDate());
                System.out.println(transaction.getDate());
            }
        }
        dates.sort(Collections.reverseOrder());
        model.addAttribute("dates", dates);

        return "transaction-list";
    }

    @RequestMapping("/transaction-update/{id}")
    public String updateTransaction(@PathVariable("id") Long transactionId, Model model){
        List<Account> accounts = accountService.getAccounts();
        model.addAttribute("accounts", accounts);
        Transaction transaction = transactionService.getTransactionById(transactionId);
        if (transaction == null){
            return "resource-not-found";
        }
        model.addAttribute("transaction", transaction);

        return "transaction-update";
    }
}


