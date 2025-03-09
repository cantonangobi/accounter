package com.example.accounter;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private String accountID;
    private String accountName;
    private double  balance;
    // private double totalIncome;
    // private double totalExpenses;
    private List<Transaction> transactions;
    // private String lastTXID = "0";
    // private String lastAccID = "0";

    // public Account(){
    //     // lastTXID = "0";
    //     accountID = "1";
    //     name = "";
    //     balance = 0;
    //     transactions = new ArrayList<Transaction>();
    // }

    public Account(String accID, String name, double balance){
        this.accountID = accID;
        this.accountName = name;
        this.balance = balance;
        transactions = new ArrayList<Transaction>();
    }
    public Account(String accID, String name, double balance, ArrayList<Transaction> transactions ){
        this.accountID = accID;
        this.accountName = name;
        this.balance = balance;
        this.transactions = transactions;
    }

    public void addTransaction(String txID, String type, double amount){
        
        Transaction transaction = new Transaction(txID, accountName, type, amount, balance);
        transactions.add(transaction);

    }

    public String toString(){
        return accountID + "," +accountName + "," + balance;
    }

    //setters and getters
    public void setTransactions(List<Transaction> transactions){
        this.transactions = transactions;
    }
    public List<Transaction> geTransactions(){
        return transactions;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double amount){
        balance = amount;
    }

    public void setAccountID(String userID){
        this.accountID = userID;
    }
    
    public void setAccountName(String name){
        this.accountName = name;
    }

    public String getAccountID(){
        return accountID;
    }
    public String getAccountName(){
        return accountName;
    }
    
    
}
