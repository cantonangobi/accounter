package com.example.accounter;

public class User {
    private String userID;
    private String username;
    private String email;
    private String password;

    public User(String username, String email, String password){
        this.userID = "1";
        this.username = username;
        this.email = email;
        this.password = hash(password);
        
    }
    public User(String userID, String username, String email, String password){
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.password = hash(password);
    }


    private String hash(String text){
        String hashed = "";
        for (int i = 0; i < text.length(); i++){
            hashed = hashed + (text.charAt(i) + 5);
        }
        return hashed;
    }

    public String toString(){
        return userID + "," + username + "," + email + "," + password;
    }

    public String getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setPassword(String password) {  
        this.password = hash(password);
    }

    

    public void setUsername(String username) {
        this.username = username;
    }
}
