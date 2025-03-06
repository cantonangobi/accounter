package com.example.accounter;

public class IOHandler {
    private String name;
    private String amount;
    private String type;
    private String balance;

    public IOHandler(){
        name = "";
        amount = "";
        type = "";
        balance = "";
    }
    public String getName() {
        return name;
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

    public void setName(String name) {
        this.name = name;
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
}
