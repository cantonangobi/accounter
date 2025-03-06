package com.example.accounter;

public class Transaction {
    private String transactionID;
    private String type;
    private double amount;
    private double runningBalance;

    Transaction(String id, String type, double amount, double balance){
        this.transactionID = id;
        this.type = type;
        this.amount = amount;
        this.runningBalance = balance;
    }

    public String getID(){
        return transactionID;
    }
    public String getType(){
        return type;
    }
    public double getAmount(){
        return amount;
    }
    public double getBalance(){
        return runningBalance;
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
}
