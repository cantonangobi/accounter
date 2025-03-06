package com.example.accounter;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppController {
    private Accounter accounter = new Accounter();

    @RequestMapping("/")
    public String home(IOHandler output, Model model){

        if (accounter.account == null){
            return "redirect:signup";
        }

        output = new IOHandler();
        output.setName(accounter.account.getName());
        output.setAmount(String.valueOf(accounter.account.getBalance()));
        model.addAttribute("output", output);

        // System.out.println("Model: " + model);
        // System.out.println("name: " +output.getName() + " amount: " + output.getAmount());

        return "index";
    }
    @RequestMapping("/signup")
    public String signup(IOHandler input, Model model){
        model.addAttribute("input", input);
        input = (IOHandler) model.getAttribute("input");
        
        if (!input.getName().isEmpty() &&  !input.getAmount().isEmpty() ){
            String name = input.getName();
            Double amount = Double.parseDouble(input.getAmount());
            accounter.createAccount(name, amount);      
            return "redirect:/";
        }

        // System.out.println("Model: " + model);
        // System.out.println("name: " +name + " amount: " + amount);

        return "signup";
    }
    @RequestMapping("/changebalance")
    public String changebalance(IOHandler input, Model model){
        model.addAttribute("input", input);    
        input = (IOHandler) model.getAttribute("input");
    
        if (!input.getAmount().isEmpty()){
            Double amount = Double.parseDouble(input.getAmount());
            accounter.setBalance(amount);
            return "redirect:/";
        }
        
        // System.out.println("Model: " + model);
        // System.out.println("amount: " + amount);      

        return "changebalance";
    }
    @RequestMapping("/newrecord")
    public String newrecord(IOHandler input, Model model){
        model.addAttribute("input", input); 
        input = (IOHandler) model.getAttribute("input");
        
        if (!input.getAmount().isEmpty() && !input.getType().isEmpty()){
            String type = input.getType();
            Double amount = Double.parseDouble(input.getAmount());
            
            if (type.equals( "expense")){
                accounter.recordExpense(amount);
            }
            else if (type.equals("income")){
                accounter.recordIncome(amount);
            }
            return "redirect:/";
        }
        
        // System.out.println("Model: " + model);
        // System.out.println("type: " + type);
        // System.out.println("amount: " + amount);
        
        return "newrecord";
    }
    
    @RequestMapping("/transactions")
    public String transactions(IOHandler output, Model model){
        List<Transaction> transactions = accounter.getTransactions();
        List<IOHandler> rows = new ArrayList<>();
        if(!transactions.isEmpty()){
            for (Transaction transaction : transactions){
                output = new IOHandler();
                output.setType(transaction.getType());
                output.setAmount(String.valueOf(transaction.getAmount()));
                output.setBalance(String.valueOf(transaction.getBalance()));
                rows.add(output);
            }
           
        }
        model.addAttribute("rows", rows);
        return "transactions";
    }
}
