package com.example.accounter;

import java.util.ArrayList;

public class Account {
    private String userID;
    private String name;
    private double  balance;
    private ArrayList<Transaction> transactions;
    private String lastTXID = "0";

    public Account(){
        // lastTXID = "0";
        userID = "1";
        name = "";
        balance = 0;
        transactions = new ArrayList<Transaction>();
    }

    public Account(String userID, String name, double balance){
        this.userID = userID;
        this.name = name;
        this.balance = balance;
        transactions = new ArrayList<Transaction>();
    }
    public Account(String userID, String name, double balance,ArrayList<Transaction> transactions ){
        this.userID = userID;
        this.name = name;
        this.balance = balance;
        this.transactions = transactions;
    }

    public void addTransaction(String type, double amount){
        lastTXID = String.valueOf(Integer.parseInt(lastTXID) + 1);
        Transaction transaction = new Transaction(lastTXID, type, amount, balance);
        transactions.add(transaction);

    }
    public void setTransactions(ArrayList<Transaction> transactions){
        this.transactions = transactions;
    }
    public ArrayList<Transaction> geTransactions(){
        return transactions;
    }

    public double getBalance(){
        return balance;
    }

    public void setBalance(double amount){
        balance = amount;
    }

    public void setUserID(String userID){
        this.userID = userID;
    }
    
    public void setName(String name){
        this.name = name;
    }

    public String getUserID(){
        return userID;
    }
    public String getName(){
        return name;
    }
    public void setLastTXID(String txID){
        this.lastTXID = txID;
    }
    
}
