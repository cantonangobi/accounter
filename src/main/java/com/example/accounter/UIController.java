package com.example.accounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UIController {
    private Accounter accounter = new Accounter();

    @RequestMapping("/")
    public String home(IODataHandler user, Model model){

        if (!accounter.userExists()){
            return "redirect:signup";
        }

        user = new IODataHandler();
        user.setUsername(accounter.getUserName());
        model.addAttribute("user", user);
        // data.setPassword(null);
        // data.setAmount(String.valueOf(accounter.getBalance()));
        List<Account> accounts = accounter.getAccounts();
        model.addAttribute("accounts", accounts);

        

        // System.out.println("Model: " + model);
        // System.out.println("name: " +output.getName() + " amount: " + output.getAmount());

        return "index";
    }
    
    @RequestMapping("/signup")
    public String signup(IODataHandler data, Model model){
        model.addAttribute("data", data);
        data = (IODataHandler) model.getAttribute("data");
        
        if (!data.getUsername().isEmpty() &&  !data.getEmail().isEmpty() && !data.getPassword().isEmpty() ){
            String username = data.getUsername();
            String password = data.getPassword();
            String email = data.getEmail();
            accounter.createUser(username, email, password);
            // accounter.createAccount(name, amount); 
            return "redirect:/";
        }

        // System.out.println("Model: " + model);
        // System.out.println("name: " +name + " amount: " + amount);

        return "signup";
    }

    @RequestMapping("/newaccount")
    public String newaccount(IODataHandler data, Model model){
        model.addAttribute("data", data);
        data = (IODataHandler) model.getAttribute("data");

        if (!data.getAccountname().isEmpty() && !data.getAmount().isEmpty()){
            String accountName = data.getAccountname();
            Double amount = Double.parseDouble(data.getAmount());
            accounter.addAccount(accountName, amount);
            return "redirect:/";
        }

        return "newaccount";
    }
    
    @RequestMapping("/changebalance")
    public String changealance(IODataHandler data, Model model){
        model.addAttribute("data", data);    
        data = (IODataHandler) model.getAttribute("data");
        List<Account> accounts = accounter.getAccounts();
        model.addAttribute("accounts", accounts);
     
        if (!data.getAmount().isEmpty() && !data.getAccountname().isEmpty()){
            String accountName = data.getAccountname();
            Double amount = Double.parseDouble(data.getAmount());
            accounter.setBalance(accountName, amount);
            return "redirect:/";
        }
        
        // System.out.println("Model: " + model);
        // System.out.println("amount: " + amount);      

        return "changebalance";
    }
    
    @RequestMapping("/newrecord")
    public String newrecord(IODataHandler data, Model model){
        model.addAttribute("data", data); 
        data = (IODataHandler) model.getAttribute("data");
        List<Account> accounts = accounter.getAccounts();
        model.addAttribute("accounts", accounts);
        
        if (!data.getAmount().isEmpty() && !data.getType().isEmpty() && !data.getAccountname().isEmpty()){
            String accountName = data.getAccountname();
            String type = data.getType();
            Double amount = Double.parseDouble(data.getAmount());
            
            if (type.equals( "expense")){
                accounter.recordExpense(accountName,amount);
            }
            else if (type.equals("income")){
                accounter.recordIncome(accountName,amount);
            }
            return "redirect:/";
        }
        
        // System.out.println("Model: " + model);
        // System.out.println("type: " + type);
        // System.out.println("amount: " + amount);
        
        return "newrecord";
    }
    
    @RequestMapping("/transactionlist")
    public String transactionlist(IODataHandler data, Model model){
        model.addAttribute("data", data);
        data = (IODataHandler) model.getAttribute("data");

        List<Account> accounts = accounter.getAccounts();
       

        model.addAttribute("accounts", accounts);

        System.out.println(model.getAttribute("data"));
        System.out.println(data.getSelections());

        List<Transaction> transactions = new ArrayList<>();
        if (!data.getSelections().isEmpty()) {
            for (String accountName : data.getSelections()){
                transactions.addAll(accounter.getTransactions(accountName));
            }
            
        }
        else{
            // for (Account account : accounts){
            //     transactions.addAll(accounter.getTransactions(account.getAccountName()));
            // }
            List<String> options = new ArrayList<>();
            for (Account account : accounts){
                options.add(account.getAccountName());
                transactions.addAll(accounter.getTransactions(account.getAccountName()));
            }
            
            data.setSelections(options);
        }

        // List<Transaction> transactions = accounter.getTransactions("Mpesa");
        // System.out.println("Transactions size: " + transactions.size());
        
        
        model.addAttribute("transactions", transactions);
        return "transactionlist";
    }
}
