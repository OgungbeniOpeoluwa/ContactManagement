package org.example.util;

public class Verification {

    public static  boolean verifyEmail(String email){
        return email.matches("[a-zA-Z\\d+/_@.-]+@[a-z]+[/.][a-z]{2,3}");
    }
   public static boolean verifyPhoneNumber(String phoneNumber){
       return phoneNumber.matches("[+][1-9][0-9]{6,12}");
    }
    public static boolean verifyPassword(String password){
        return password.matches("[A-Z][A-Za-z0-9/@-_.?:^&!(){}#*%$]{8,20}");
    }



}
