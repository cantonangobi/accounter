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

    @RequestMapping("/account-create")
    public String newAccount(){
        return "account-create";
    }

    @RequestMapping("/deleteaccount")
    public String deleteAccount(Model model){
        List<Account> accounts = accountService.getAccounts();
        model.addAttribute("accounts", accounts);
        return "deleteaccount";
        
    }

    @RequestMapping("/addrecord")
    public String addRecord(Model model){
        List<Account> accounts = accountService.getAccounts();
        model.addAttribute("accounts", accounts);
        return "addrecord";
    }

    @RequestMapping("/account-list")
    public String accountList(Model model){
        List<Account> accounts = accountService.getAccounts();

        model.addAttribute("accounts", accounts);        

        return "account-list";
    }

    @RequestMapping("/account-details/{name}")
    public String accountDetails(@PathVariable("name") String accountName, Model model){
        System.out.println(accountName);
        Account account = accountService.getAccount(accountName);
        List<Transaction> transactions = transactionService.getAccountTransactions(accountName);
        if (account == null) {
            return "resource-not-found";
        }

        model.addAttribute("account", account);
        model.addAttribute("transactions", transactions);
        return "account-details";
    }

    // @RequestMapping("/error")
    // public String notFound(){
    //     return "resource-not-found";
    // }

    @RequestMapping("/transaction-list")
    public String TransactionList(Model model){
        List<Account> accounts = accountService.getAccounts();
        List<Transaction> transactions = transactionService.getUserTransactions();

        model.addAttribute("accounts", accounts);
        model.addAttribute("transactions", transactions);
        
        System.out.println(model.getAttribute("transactions"));

        return "transaction-list";
    }

    @RequestMapping("/transaction-create")
    public String TransactionCreate(Model model){
        List<Account> accounts = accountService.getAccounts();
        model.addAttribute("accounts", accounts);

        return "transaction-create";
    }

    
}


