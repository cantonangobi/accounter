package com.example.accounter;

import java.util.ArrayList;
import java.util.List;

public class IODataHandler {
    private String username;
    private String accountname;
    private String email;
    private String password;
    private String amount;
    private String type;
    private String balance;
    private List<String> selections;
    

    public IODataHandler(){
        username = "";
        email="";
        password = "";
        accountname = "";
        amount = "";
        type = "";
        balance = "";
        selections = new ArrayList<>();
        
    }
    
    public String getUsername() {
        return username;
    }
    public String getAccountname() {
        return accountname;
    }
    public String getEmail() {
        return email;
    }
    public String getType() {
        return type;
    }
    public String getAmount() {
        return amount;
    }

    public String getBalance() {
        return balance;
    }
    public String getPassword() {
        return password;
    }
    public List<String> getSelections() {
        return selections;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccountname(String accountname) {
        this.accountname = accountname;
    }
    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }
    public void setSelections(List<String> selections) {
        this.selections = selections;
    }
}
