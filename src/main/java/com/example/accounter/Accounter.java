package com.example.accounter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Accounter {
    private User user;
    // private Account account;
    private List<Account> accounts;
    private List<Transaction> transactions;
    private String nextTxID;
    private String nextAccID;
    private String userFileName, accountFileName, transactionFileName;
    private boolean userExists; 


    public Accounter(){
        accounts = new ArrayList<>();
        userFileName = "users.csv";
        accountFileName = "accounts.csv";
        // transactionFileName = "";
        nextAccID = "0";
        nextTxID = "0";
        userExists = false;
        load();
    }

    // public void createAccount(){
    //     account = new Account();    
    // }
    public void createUser(String username, String email, String password){
        user = new User(username, email, password);
        userExists = true;
        save();
    }

    public String getUserName() {
        return user.getUsername();
    }

    public void addAccount(String name, double amount){
        generateAccID();
        Account account = new Account(nextAccID, name, amount);
        accounts.add(account);
        save();
        // userExists = true;
    }
    
    public Account getAccount(String accName){
        for(Account account : accounts){
            if (account.getAccountName().equals(accName)){
                return account;
            }
        }
        return null;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    // public boolean accountExists(String accID){
    //     for(Account account : accounts){
    //         if (account.getAccountID().equals(accID)){
    //             return true;
    //         }
    //     }
    //     return false;
    // }

    public void setBalance(String accName, double amount){
        Account account = getAccount(accName);
        if (account != null){
            double difference = amount - account.getBalance();
       
            if (difference > 0){
                recordIncome(accName, difference);
                }
                else{
                    recordExpense(accName, Math.abs(difference));
                }
        
            save();
        }
        
    }

    public double getBalance(String accName){
        Account account = getAccount(accName);
        if (account != null){
            return account.getBalance();
        }
        return 0;
    }

    public void recordIncome(String accName, double amount){
        Account account = getAccount(accName);
        if (account != null){
            account.setBalance(account.getBalance() + amount);
            generateTxID();
            account.addTransaction(nextTxID,"income", amount);
            save();
        }
        
    }

    public void recordExpense(String accName, double amount){
        Account account = getAccount(accName);
        if (account != null){
            account.setBalance(account.getBalance() - amount);
            generateTxID();
            account.addTransaction(nextTxID,"expense", amount);
            save();
        }

    }

    // public void resetAccount(String name, double amount){
    //     // lastID = String.valueOf(Integer.parseInt(lastID) + 1);
    //     account = new Account(account.getAccountID(),name,amount);
    //     save();
    //     userExists = true;
    // }

    public void generateTxID(){
        nextTxID = String.valueOf(Integer.parseInt(nextTxID) + 1);
    }
    public void generateAccID(){
        nextAccID = String.valueOf(Integer.parseInt(nextAccID) + 1);
    }
    public boolean userExists(){
        return userExists;
    }

    public String getAccountName(String accID){
        Account account = getAccount(accID);
        if (account != null) {
            return account.getAccountName();        
        }
        return "";
    }

    public List<Transaction> getTransactions(String accName){
        Account account = getAccount(accName);
        if (account != null) {
            return account.geTransactions();        
        }
        return null;
    }

    private void save(){
        // File file = new File(userFileName);
        String userFileContent = user.toString();
        writeFile(userFileName, userFileContent, false);

        // file = new File(accountFileName);
        String accountFileContent = "";

        for (Account account : accounts){
            // String serializedAccount = ;
            accountFileContent = accountFileContent + account.toString() +"\n";
            
            transactionFileName = account.getAccountName() + "transactions.csv";
            String transactionFileContent = "";
            List<Transaction> transactions = account.geTransactions();
            for (Transaction transaction : transactions) {
                // String serializedTransaction = ;
                transactionFileContent = transactionFileContent + transaction.toString() + "\n";    
            }  
            writeFile(transactionFileName, transactionFileContent, false);

        }
        System.out.println(accountFileContent);
        writeFile(accountFileName, accountFileContent, false);

        // for (Account account: accounts){
        //     transactionFileName = "transactions" + account.getAccountID() + ".csv";
        //     file = new File(transactionFileName);
        //     String transactionFileContent = "";
        //     ArrayList<Transaction> transactions = account.geTransactions();
        //     for (Transaction transaction : transactions) {
        //         String line = transaction.getID() + "," + transaction.getAccountID() + "," + transaction.getType() + "," + transaction.getAmount() + "," + transaction.getBalance() + "\n";
        //         transactionFileContent = transactionFileContent + line;    
        //     }  
        //     writeFile(file, transactionFileContent, false);
        // }
        
    }
    

    private void load(){ 
        
        // if (!file.isFile()) {
        //     return;
        // }

        String userFileContent = readFile(userFileName);
        if (userFileContent.isEmpty()) return;
        String[] userAttributes = userFileContent.split(",");
        user = new User(userAttributes[0], userAttributes[1], userAttributes[2], userAttributes[3]);
        userExists = true;

        // file = new File(accountFileName);
        // if (!file.isFile()) {
        //     return;
        // }
        
        String accountFileContent = readFile(accountFileName);
        if (accountFileContent.isEmpty()) return;
        String[] accountFileLines = accountFileContent.split("\n");
        for (String accountFileLine : accountFileLines){
            if (!accountFileLine.isEmpty())
            {
                String[] accountAttributes = accountFileLine.split(",");
                Account account = new Account(accountAttributes[0],accountAttributes[1], Double.parseDouble(accountAttributes[2]));
                accounts.add(account);
                if (Integer.parseInt(account.getAccountID()) > Integer.parseInt(nextTxID)){
                    nextAccID = accountAttributes[0];
                }
                    

                transactionFileName = account.getAccountName() + "transactions.csv";
                String transactionFileContent = readFile(transactionFileName);
                if (!transactionFileContent.isEmpty())
                {
                    transactions = new ArrayList<Transaction>();
                    String[] transactionFileLines = transactionFileContent.split("\n");
                    for (String transactionFileLine : transactionFileLines) 
                    {
                        String[] transactionAttributes = transactionFileLine.split(",");
                        Transaction transaction = new Transaction(transactionAttributes[0], transactionAttributes[1], transactionAttributes[2], Double.parseDouble(transactionAttributes[3]), Double.parseDouble(transactionAttributes[4]));
                        transactions.add(transaction);
                        if (Integer.parseInt(transaction.getTransactionID()) > Integer.parseInt(nextTxID))
                        {
                            nextTxID = transaction.getTransactionID();
                        }
                        
                    }
                    account.setTransactions(transactions);
                }

                

                // System.out.println(account.getName());
            }
        }
       

        // file = new File(transactionFileName);
        // if (!file.isFile()) {
        //     return;
        // }

        
        // String transactionFileContent = readFile(transactionFileName);
        // if (accountFileContent.isEmpty()) return;
        // transactions = new ArrayList<Transaction>();
        // String[] transactionFileLines = transactionFileContent.split("\n");
        // for (String line : transactionFileLines) {
        //     String[] transactionAttributes = line.split(",");
        //     Transaction transaction = new Transaction(transactionAttributes[0], transactionAttributes[1], transactionAttributes[2], Double.parseDouble(transactionAttributes[3]), Double.parseDouble(transactionAttributes[4]));
        //     transactions.add(transaction);


        //     nextTxID = transactionAttributes[0];
        // }

        
    }

    
    private void writeFile(String fileName, String content, boolean append){
        try {
            File file = new File(fileName);
            FileWriter writer = new FileWriter(file, false);
            writer.write(content);
            writer.close();
            System.out.println("The file has been written in " + System.getProperty("user.dir"));

        } catch (IOException e) {
            System.out.println("Something went wrong");
            e.printStackTrace();
        }
        
    }

    private String readFile(String filename){
        String content = "";
        File file = new File(filename);
        if (file.isFile()){
            try{
                Scanner reader = new Scanner(file);
    
                // Traversing File Data
                  while (reader.hasNextLine()) {
                    content = content + reader.nextLine() + "\n";
                    // lines = lines + "\n";
                }
                // if (reader.hasNextLine()){
                //     line = reader.nextLine();
                // }
                
                reader.close();
    
            } catch (IOException e){
                System.out.println("Something went wrong");
                e.printStackTrace();
            }
        }
        
        
        return content;
    }


}
