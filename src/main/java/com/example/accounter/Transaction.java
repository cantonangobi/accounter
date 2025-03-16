package com.example.accounter;

import java.util.Date;

public class Transaction {
    private String transactionID;
    private String accountName;
    private String type;
    private double amount;
    private double runningBalance;
    private Date date;
    

    Transaction(String txid, String accountName, String type, double amount, double balance){
        this.transactionID = txid;
        this.accountName = accountName;
        this.type = type;
        this.amount = amount;
        this.runningBalance = balance;
        this.date = new Date();
    }

    public String toString(){
        return transactionID + "," + accountName + "," + type + "," + amount + "," + runningBalance;
    }

   
    //getters and setters
    public String getType(){
        return type;
    }
    public double getAmount(){
        return amount;
    }
    public double getBalance(){
        return runningBalance;
    }
    public String getTransactionID() {
        return transactionID;
    }
    public String getAccountName() {
        return accountName;
    }


    public void setID(String id){
        this.transactionID = id;
    }
    public void setType(String type){
        this.type = type;
    }
    public void setAmount(double amount){
        this.amount = amount;
    }
    public void setBalance(double balance){
        this.runningBalance = balance;
    }
    public void setTransactionID(String transactionID) {
        this.transactionID = transactionID;
    }
    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
    public Date getDate() {
        return date;
    }
}
