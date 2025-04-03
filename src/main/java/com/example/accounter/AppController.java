package com.example.accounter;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.accounter.account.Account;
import com.example.accounter.account.AccountService;
import com.example.accounter.user.UserService;

import lombok.AllArgsConstructor;

@Controller
@AllArgsConstructor
public class AppController {
    private AccountService accountService;
    private UserService userService;

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

    @RequestMapping("/createaccount")
    public String newAccount(){
        return "createaccount";
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

    
}


