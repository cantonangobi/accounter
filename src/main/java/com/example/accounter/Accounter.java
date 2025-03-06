package com.example.accounter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Accounter {
    public Account account;
    // private String lastID;
    private String accountFileName, transactionFileName;
    private boolean accountLoaded; 


    public Accounter(){
        accountFileName = "account.csv";
        transactionFileName = "transactions.csv";
        accountLoaded = false;
        load();
    }

    // public void createAccount(){
    //     account = new Account();    
    // }

    public void createAccount(String name, double amount){
        account = new Account("1", name, amount );    
        save();
        accountLoaded = true;
    }

    public void setBalance(double amount){
        double difference = amount - account.getBalance();
        account.setBalance(amount);
        account.addTransaction("adjustment", difference);
        save();
    }

    public double getBalance(){
        return account.getBalance();
    }

    public void recordIncome(double amount){
        account.setBalance(account.getBalance() + amount);
        account.addTransaction("income", amount);
        save();
    }

    public void recordExpense(double amount){
        account.setBalance(account.getBalance() - amount);
        account.addTransaction("expense", amount);
        save();
    }

    public void resetAccount(String name, double amount){
        // lastID = String.valueOf(Integer.parseInt(lastID) + 1);
        account = new Account(account.getUserID(),name,amount);
        save();
        accountLoaded = true;
    }

    public ArrayList<Transaction> getTransactions(){
        return account.geTransactions();
    }

    private void save(){
        // boolean append = false;
        File file = new File(accountFileName);
        String line = account.getUserID() + "," + account.getName() + "," + String.valueOf(account.getBalance() +"\n");
        writeFile(file, line, false);

        file = new File(transactionFileName);
        String content = "";
        ArrayList<Transaction> transactions = account.geTransactions();
        for (Transaction transaction : transactions) {
            line = transaction.getID() + "," + transaction.getType() + "," + transaction.getAmount() + "," + transaction.getBalance() + "\n";
            content = content + line;
            
        }
        writeFile(file, content, false);
    }
    

    private void load(){ 
        File file = new File(accountFileName);
        
        if (file.isFile()) {
            String line = readFile(file);
            if (!line.isEmpty()){
                String[] words = line.split(",");
                account = new Account(words[0],words[1], Double.parseDouble(words[2]));
                System.out.println(account.getName());
                accountLoaded = true;
            }
        }

        file = new File(transactionFileName);
        if (file.isFile()){
            String content = readFile(file);
            if (!content.isEmpty()){
                ArrayList<Transaction> transactions = new ArrayList<Transaction>();
                String[] lines = content.split("\n");
                for (String line : lines) {
                    String[] words = line.split(",");
                    transactions.add(new Transaction(words[0], words[1], Double.parseDouble(words[2]), Double.parseDouble(words[3])));
                    account.setLastTXID(words[0]);
                }
                account.setTransactions(transactions);

            }
        }
    }

    public boolean accountExists(){
        return accountLoaded;
    }

    public String getAccountName(){
        return account.getName();
    }

    private void writeFile(File file, String line, boolean append){
        try {

            FileWriter writer = new FileWriter(file, append);

            writer.write(line);
            writer.close();
            System.out.println("The file has been written in " + System.getProperty("user.dir"));

        } catch (IOException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        
    }

    private String readFile(File file){
        String lines = "";
        try{
            Scanner reader = new Scanner(file);

            // Traversing File Data
              while (reader.hasNextLine()) {
                lines = lines + reader.nextLine();
                lines = lines + "\n";
            }
            // if (reader.hasNextLine()){
            //     line = reader.nextLine();
            // }
            
            reader.close();

        } catch (IOException e){
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        
        return lines;
    }


}
