package com.example.accounter;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.example.accounter.account.Account;
import com.example.accounter.account.AccountService;
import com.example.accounter.transaction.Transaction;
import com.example.accounter.transaction.TransactionService;
import com.example.accounter.user.UserService;
import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AppController {
    private AccountService accountService;
    private UserService userService;
    private TransactionService transactionService;

    @RequestMapping("/")
    public String index(Model model){
        String userName = userService.getSessionUser().getUsername();
        List<Account> accounts = accountService.getAccounts();
        model.addAttribute("accounts", accounts);
        model.addAttribute("userName", userName);        
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

    
    @RequestMapping("/account-list")
    public String getAccountList(Model model){
        List<Account> accounts = accountService.getAccounts();

        model.addAttribute("accounts", accounts);        

        return "account-list";
    }

    @RequestMapping("/account-details/{id}")
    public String getAccountDetails(@PathVariable("id") Long accountId, Model model){
        // System.out.println(accountId);
        Account account = accountService.getAccount(accountId);
        if (account == null) {
            return "resource-not-found";
        }
        model.addAttribute("account", account);

        List<Transaction> transactions = transactionService.getAccountTransactions(accountId);
        model.addAttribute("transactions", transactions);
        return "account-details";
    }

    //This will connect to the backend using javascript and fetch api
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
        // model.addAttribute("transactions", transactions);
        return "account-update";
    }

    //This will connect to the backend using javascript and fetch api
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



    

   
    // @RequestMapping("/error")
    // public String notFound(){
    //     return "resource-not-found";
    // }

    @RequestMapping("/transaction-list")
    public String getTransactionList(Model model){
        List<Account> accounts = accountService.getAccounts();
        List<Transaction> transactions = transactionService.getUserTransactions();

        model.addAttribute("accounts", accounts);
        model.addAttribute("transactions", transactions);
        
        System.out.println(model.getAttribute("transactions"));

        return "transaction-list";
    }

    @RequestMapping("/transaction-create")
    public String createTransaction(Model model){
        List<Account> accounts = accountService.getAccounts();
        model.addAttribute("accounts", accounts);

        return "transaction-create";
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
    
    // @RequestMapping("/transaction-delete/{id}")
    // public String deleteTransaction(@PathVariable("id") Long transactionId, Model model){

    // }
}


