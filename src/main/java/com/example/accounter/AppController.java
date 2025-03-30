package com.example.accounter;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.accounter.account.Account;
import com.example.accounter.account.AccountService;
import com.example.accounter.user.AppUser;
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

        

        System.out.println("Model: " + model);
        // System.out.println("name: " +output.getName() + " amount: " + output.getAmount());

        return "index";
    }

    @RequestMapping("/signup")
    public String signup(){
        return "signup";
    }

    @RequestMapping("/newaccount")
    public String newAccount(){
        return "newaccount";
    }

    
}


