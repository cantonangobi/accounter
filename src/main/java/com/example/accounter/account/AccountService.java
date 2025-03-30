package com.example.accounter.account;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.example.accounter.user.AppUser;
import com.example.accounter.user.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserService userService;

    public List<Account> getAccounts(){
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        // AppUser user =  (AppUser) auth.getPrincipal();

        AppUser user = userService.getSessionUser();

        return accountRepository.findAllByUserId(user.getUserId());
    }

    // public Account geAccount(String name){
    //     return accountRepository.findAccountByName(name)
    // }
    public String addNewAccount(Account account){
        //get the current logged in user
        // Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = userService.getSessionUser();

        account.setUserId(user.getUserId());
        boolean accountExists = accountRepository.findByUserIdAndName(account.getUserId(), account.getName()).isPresent();

        if (accountExists){
            System.out.println("Account not added, already exists");
            return "Account not added, already exists";
        }

        //todo: get the actual userId
        // Long userId = (long) 1;

        
        accountRepository.save(account);


        return "Account added";
    }
}
