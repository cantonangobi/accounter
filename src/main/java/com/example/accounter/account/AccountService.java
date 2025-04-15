package com.example.accounter.account;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.accounter.user.AppUser;
import com.example.accounter.user.UserService;

@Service
// @AllArgsConstructor
public class AccountService {
    private final AccountRepository accountRepository;
    private final UserService userService;
    private AppUser user;

    public AccountService(AccountRepository accountRepository, UserService userService){
        this.accountRepository = accountRepository;
        this.userService = userService;
    }

    public String createAccount(Account account){
        this.user = this.userService.getSessionUser();
        account.setUserId(user.getUserId());
        boolean accountExists = accountRepository.findByUserIdAndName(account.getUserId(), account.getName()).isPresent();
        if (accountExists){
            System.out.println("Account not added, already exists");
            return "Account not added, already exists";
        }
        accountRepository.save(account);
        return "Success";
    }

    public List<Account> getAccounts(){
        this.user = this.userService.getSessionUser();
        return accountRepository.findAllByUserId(user.getUserId()).get();
    }

    public Account getAccount(String name){
        this.user = this.userService.getSessionUser();
        boolean accountExists = accountRepository.findByUserIdAndName(user.getUserId(), name).isPresent();
        if (!accountExists){
            System.out.println("Account not found");
            return null;
        }
        Account account = accountRepository.findByUserIdAndName(user.getUserId(), name).get();
        return account;
    }

    
    public Account getAccount(Long accountId){
        this.user = this.userService.getSessionUser();
        boolean accountExists = accountRepository.findByAccountIdAndUserId(accountId, user.getUserId()).isPresent();
        if (!accountExists){
            System.out.println("Account not found");
            return null;
        }
        Account account = accountRepository.getReferenceById(accountId);
        return account;
    }


    public String updateAccount(Long accountId, Account account){
        Account currentAccount = getAccount(accountId);
        // boolean accountExists = accountRepository.existsById(account.getAccountId());
        if (currentAccount == null){
            System.out.println("Account doesn't exist");
            return "Account doesn't exist";
        }
        
        currentAccount.setName(account.getName());
        currentAccount.setBalance(account.getBalance());

        accountRepository.save(currentAccount);
        return "Success";
    }

   

    public String deleteAccount(Long accountId){
        this.user = this.userService.getSessionUser();
        boolean accountExists  = accountRepository.findByAccountIdAndUserId(accountId, user.getUserId()).isPresent();
        if (!accountExists){
            System.out.println("Account does not exist");
            return "Account does not exist";
        }
        Account account = accountRepository.getReferenceById(accountId);
        System.out.println(account.toString());
        accountRepository.deleteById(accountId);

        return "Success";
    }


    public String deleteAccount(String name){
        this.user = this.userService.getSessionUser();
        boolean accountExists  = accountRepository.findByUserIdAndName(user.getUserId(), name).isPresent();
        if (!accountExists){
            System.out.println("Account does not exist");
            return "Account does not exist";
        }
        Account account = accountRepository.findByUserIdAndName(user.getUserId(), name).get();
        System.out.println(account.toString());
        accountRepository.delete(account);
        // accountRepository.deleteByAccountId(account.getAccountId());

        return "Success";
    }

    public AppUser getUser(){
        return userService.getSessionUser();
    }
}
